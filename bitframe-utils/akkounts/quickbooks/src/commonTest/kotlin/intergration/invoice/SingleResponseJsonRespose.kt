package intergration.invoice

internal val SingleResponseJsonRespose = """
{
  "Invoice": {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "248",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-07-15T20:52:48-07:00",
      "LastUpdatedTime": "2021-07-15T20:52:48-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-07-16",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Amount": 2000.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "237",
            "name": "A Test Service - 1626407559728"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "854",
            "name": "Income From External Invoice Items - AUD"
          },
          "TaxCodeRef": {
            "value": "35"
          }
        }
      },
      {
        "Amount": 2000.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 300.0,
      "TaxLine": [
        {
          "Amount": 300.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "56"
            },
            "PercentBased": true,
            "TaxPercent": 15,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "ShipFromAddr": {
      "Id": "90",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-08-15",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2300.0,
    "HomeTotalAmt": 2300.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2300.0,
    "HomeBalance": 2300.0
  },
  "time": "2021-07-15T20:52:48.585-07:00"
}
"""