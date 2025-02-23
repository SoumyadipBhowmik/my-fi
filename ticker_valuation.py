import asyncio
from collections import defaultdict
from fastapi import FastAPI, HTTPException, Query
from fastapi.middleware.cors import CORSMiddleware
import httpx
import uvicorn
from typing import Optional, List
from pydantic import BaseModel
from datetime import datetime
import os
from dotenv import load_dotenv

load_dotenv()

def get_env_vars(base_url_key="BASE_URL", api_key_key="API_KEY"):
    base_url = os.environ.get(base_url_key)
    api_key = os.environ.get(api_key_key)
    if base_url is None:
        print(f"Warning: Environment variable '{base_url_key}' not found.")
    if api_key is None:
        print(f"Warning: Environment variable '{api_key_key}' not found.")
    return base_url, api_key

app = FastAPI(
    title="Stock Market API Wrapper",
    description="A local API wrapper for Indian Stock Market data",
    version="1.0.0"
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

BASE_URL, RAPID_API_KEY = get_env_vars()

if not BASE_URL or not RAPID_API_KEY:
    print("Error: BASE_URL or RAPID_API_KEY not set in environment variables. API may not function correctly.")

class StockResponse(BaseModel):
    company_name: str
    current_price: dict
    year_high: str
    year_low: str
    dividend_history: list
    technical_data: list

@app.get("/")
def read_root():
    return {"message": "Welcome to Stock Market API Wrapper"}

async def fetch_stock_data(symbol: str, client: httpx.AsyncClient):
    """Helper function to fetch data for a single symbol using httpx."""
    try:
        headers = {
            "X-RapidAPI-Key": RAPID_API_KEY,
            "X-RapidAPI-Host": "indian-stock-exchange-api2.p.rapidapi.com"
        }
        response = await client.get(
            f"{BASE_URL}/stock",
            params={"name": symbol},
            headers=headers
        )
        response.raise_for_status()
        return response.json()
    except httpx.RequestError as e:
        raise HTTPException(status_code=500, detail=f"Error fetching data for {symbol}: {str(e)}")
    except KeyError as e:
        raise HTTPException(status_code=500, detail=f"Error processing data for {symbol}: Missing key {str(e)}")

@app.get("/api/stocks/")
async def get_multiple_stock_data(symbols: List[str] = Query(..., description="List of stock symbols")):
    """Get stock data for multiple symbols asynchronously."""
    async with httpx.AsyncClient() as client:
        tasks = [fetch_stock_data(symbol, client) for symbol in symbols]
        results_data = await asyncio.gather(*tasks, return_exceptions=True)

    results = {}
    for symbol, data in zip(symbols, results_data):
        if isinstance(data, HTTPException):
            results[symbol] = {"error": data.detail}
        else:
            try:
                processed_data = {
                    "company_name": data["companyName"],
                    "current_price": data["currentPrice"],
                    "year_high": data["yearHigh"],
                    "year_low": data["yearLow"],
                    "dividend_history": [
                        {
                            "date": dividend["recordDate"],
                            "type": dividend["interimOrFinal"],
                            "amount": dividend["value"],
                            "percentage": dividend["percentage"]
                        }
                        for dividend in data["stockCorporateActionData"]["dividend"]
                    ],
                    "technical_data": data["stockTechnicalData"]
                }
                results[symbol] = processed_data
            except KeyError as e:
                results[symbol] = {"error": f"Error processing data for {symbol}: Missing key {str(e)}"}

    return results

if __name__ == "__main__":
    uvicorn.run("ticker_valuation:app", host="0.0.0.0", port=8000, reload=True)