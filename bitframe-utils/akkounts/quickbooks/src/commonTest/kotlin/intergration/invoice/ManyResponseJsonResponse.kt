package intergration.invoice

internal val ManyResponseJsonResponse = """
[
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "224",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-07-12T05:34:26-07:00",
      "LastUpdatedTime": "2021-07-12T05:34:26-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-07-12",
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
            "value": "228",
            "name": "A Test Service - 1626093259265"
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
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "81",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-08-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2300.0,
    "HomeTotalAmt": 2300.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2300.0,
    "HomeBalance": 2300.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "221",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-07-12T01:46:15-07:00",
      "LastUpdatedTime": "2021-07-12T01:46:15-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-07-12",
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
            "value": "227",
            "name": "A Test Service - 1626079564176"
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
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "80",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-08-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2300.0,
    "HomeTotalAmt": 2300.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2300.0,
    "HomeBalance": 2300.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "220",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-07-12T01:43:20-07:00",
      "LastUpdatedTime": "2021-07-12T01:43:20-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-07-12",
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
            "value": "226",
            "name": "A Test Service - 1626079389698"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "854",
            "name": "Income From External Invoice Items - AUD"
          },
          "TaxCodeRef": {
            "value": "34"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "54"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "79",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-08-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "219",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-07-12T01:41:32-07:00",
      "LastUpdatedTime": "2021-07-12T01:41:32-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-07-12",
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
            "value": "225",
            "name": "A Test Service - 1626079282659"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "854",
            "name": "Income From External Invoice Items - AUD"
          },
          "TaxCodeRef": {
            "value": "34"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "54"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "78",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-08-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "218",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-23T18:52:41-07:00",
      "LastUpdatedTime": "2021-06-23T18:52:41-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-24",
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
            "value": "215",
            "name": "A Test Service - 1624499551"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "770",
            "name": "A Test Service - 1624499551 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "77",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-24",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "217",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-18T05:15:06-07:00",
      "LastUpdatedTime": "2021-06-18T05:15:06-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "209",
            "name": "A Test Service - 1624018504"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "746",
            "name": "A Test Service - 1624018504 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "76",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "216",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-18T05:01:53-07:00",
      "LastUpdatedTime": "2021-06-18T05:01:53-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "203",
            "name": "A Test Service - 1624017704"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "724",
            "name": "A Test Service - 1624017704 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "75",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "215",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-18T03:53:39-07:00",
      "LastUpdatedTime": "2021-06-18T03:53:39-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "191",
            "name": "A Test Service - 1624013604"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "681",
            "name": "A Test Service - 1624013604 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "74",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "214",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-18T03:52:17-07:00",
      "LastUpdatedTime": "2021-06-18T03:52:17-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "190",
            "name": "A Test Service - 1624013527"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "679",
            "name": "A Test Service - 1624013527 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "73",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "213",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-18T03:45:35-07:00",
      "LastUpdatedTime": "2021-06-18T03:45:35-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "189",
            "name": "A Test Service - 1624013124"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "677",
            "name": "A Test Service - 1624013124 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "72",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "212",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-18T03:43:42-07:00",
      "LastUpdatedTime": "2021-06-18T03:43:42-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "188",
            "name": "A Test Service - 1624013012"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "674",
            "name": "A Test Service - 1624013012 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "71",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "211",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-17T21:52:35-07:00",
      "LastUpdatedTime": "2021-06-17T21:52:35-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "177",
            "name": "A Test Service - 1623991940"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "653",
            "name": "A Test Service - 1623991940 Income"
          },
          "TaxCodeRef": {
            "value": "26"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "41"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "70",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "210",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-17T21:30:28-07:00",
      "LastUpdatedTime": "2021-06-17T21:30:28-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "165",
            "name": "A Test Service - 1623990614"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "621",
            "name": "A Test Service - 1623990614 Income"
          },
          "TaxCodeRef": {
            "value": "25"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "39"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "69",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "209",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-17T21:28:29-07:00",
      "LastUpdatedTime": "2021-06-17T21:28:29-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-18",
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
            "value": "164",
            "name": "A Test Service - 1623990494"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "619",
            "name": "A Test Service - 1623990494 Income"
          },
          "TaxCodeRef": {
            "value": "24"
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
      "TotalTax": 240.0,
      "TaxLine": [
        {
          "Amount": 240.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "37"
            },
            "PercentBased": true,
            "TaxPercent": 12,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "68",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-18",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2240.0,
    "HomeTotalAmt": 2240.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2240.0,
    "HomeBalance": 2240.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "208",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-11T01:03:05-07:00",
      "LastUpdatedTime": "2021-06-11T01:03:05-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "153",
            "name": "A Test Service - 1623398571"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "569",
            "name": "A Test Service - 1623398571 Income"
          },
          "TaxCodeRef": {
            "value": "8"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "67",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "207",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-11T01:01:10-07:00",
      "LastUpdatedTime": "2021-06-11T01:01:10-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "152",
            "name": "A Test Service - 1623398463"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "568",
            "name": "A Test Service - 1623398463 Income"
          },
          "TaxCodeRef": {
            "value": "8"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "66",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "206",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-11T00:54:53-07:00",
      "LastUpdatedTime": "2021-06-11T00:54:53-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "150",
            "name": "A Test Service - 1623398086"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "561",
            "name": "A Test Service - 1623398086 Income"
          },
          "TaxCodeRef": {
            "value": "8"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "65",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "205",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-11T00:52:42-07:00",
      "LastUpdatedTime": "2021-06-11T00:52:42-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "149",
            "name": "A Test Service - 1623397954"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "558",
            "name": "A Test Service - 1623397954 Income"
          },
          "TaxCodeRef": {
            "value": "8"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "64",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "204",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-11T00:51:23-07:00",
      "LastUpdatedTime": "2021-06-11T00:51:23-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "148",
            "name": "A Test Service - 1623397871"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "557",
            "name": "A Test Service - 1623397871 Income"
          },
          "TaxCodeRef": {
            "value": "8"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "63",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "203",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-10T14:40:01-07:00",
      "LastUpdatedTime": "2021-06-10T14:40:01-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "147",
            "name": "A Test Service - 1623361194"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "554",
            "name": "A Test Service - 1623361194 Income"
          },
          "TaxCodeRef": {
            "value": "6"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "6"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "62",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "202",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-10T14:29:16-07:00",
      "LastUpdatedTime": "2021-06-10T14:29:16-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "143",
            "name": "A Test Service - 1623360548"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "546",
            "name": "A Test Service - 1623360548 Income"
          },
          "TaxCodeRef": {
            "value": "5"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "4"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "61",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "201",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-10T14:28:35-07:00",
      "LastUpdatedTime": "2021-06-10T14:28:35-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "142",
            "name": "A Test Service - 1623360509"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "544",
            "name": "A Test Service - 1623360509 Income"
          },
          "TaxCodeRef": {
            "value": "6"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "6"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "60",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "200",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-10T14:20:10-07:00",
      "LastUpdatedTime": "2021-06-10T14:20:10-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "140",
            "name": "A Test Service - 1623360003"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "541",
            "name": "A Test Service - 1623360003 Income"
          },
          "TaxCodeRef": {
            "value": "8"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "59",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "199",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-10T14:19:40-07:00",
      "LastUpdatedTime": "2021-06-10T14:19:40-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
            "value": "139",
            "name": "A Test Service - 1623359952"
          },
          "ItemAccountRef": {
            "value": "538",
            "name": "A Test Service - 1623359952 Income"
          },
          "TaxCodeRef": {
            "value": "8"
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
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 2000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "102",
      "name": "Test Receiver-invoice-receiver"
    },
    "FreeFormAddress": false,
    "ShipFromAddr": {
      "Id": "58",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 2000.0,
    "HomeTotalAmt": 2000.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 2000.0,
    "HomeBalance": 2000.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "198",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-06-10T14:09:35-07:00",
      "LastUpdatedTime": "2021-06-10T14:09:35-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "TxnDate": "2021-06-11",
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
        "Amount": 15.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "2",
            "name": "General services:Hours"
          },
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "8"
          }
        }
      },
      {
        "Amount": 15.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 0,
      "TaxLine": [
        {
          "Amount": 0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "8"
            },
            "PercentBased": true,
            "TaxPercent": 0,
            "NetAmountTaxable": 15.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "2",
      "name": "Oxon Insurance Agency"
    },
    "BillAddr": {
      "Id": "3",
      "Line1": "11 Chain Road",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-38.066621",
      "Long": "145.297772"
    },
    "ShipAddr": {
      "Id": "3",
      "Line1": "11 Chain Road",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-38.066621",
      "Long": "145.297772"
    },
    "FreeFormAddress": true,
    "ShipFromAddr": {
      "Id": "57",
      "Line1": "2500 Garcia Ave",
      "Line2": "Sydney NSW  2050"
    },
    "DueDate": "2021-07-11",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 15.0,
    "HomeTotalAmt": 15.0,
    "PrintStatus": "NeedToPrint",
    "EmailStatus": "NotSet",
    "Balance": 15.0,
    "HomeBalance": 15.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "192",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-04-28T07:41:49-07:00",
      "LastUpdatedTime": "2021-04-28T07:41:49-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1022",
    "TxnDate": "2021-04-29",
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
        "Description": "Holiday party - gold level",
        "Amount": 6000.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "TaxInclusiveAmt": 6600.0,
          "ItemRef": {
            "value": "7",
            "name": "Holiday party:Gold party"
          },
          "UnitPrice": 2000,
          "Qty": 3,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 6000.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 600.0,
      "TaxLine": [
        {
          "Amount": 600.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 6000.0
          }
        }
      ]
    },
    "RecurDataRef": {
      "value": "11"
    },
    "CustomerRef": {
      "value": "17",
      "name": "Jordan Burgess"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "15",
      "Line1": "1 Success Way",
      "City": "Sydney",
      "CountrySubDivisionCode": "NSW",
      "PostalCode": "2000",
      "Lat": "-33.871226",
      "Long": "150.897656"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-05-29",
    "GlobalTaxCalculation": "TaxInclusive",
    "TotalAmt": 6600.0,
    "HomeTotalAmt": 6600.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 6600.0,
    "HomeBalance": 6600.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "185",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-04-13T07:38:37-07:00",
      "LastUpdatedTime": "2021-04-13T07:38:37-07:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1019",
    "TxnDate": "2021-04-14",
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
        "Description": "promotional items -- misc engraved items",
        "Amount": 409.09,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "TaxInclusiveAmt": 450.0,
          "ItemRef": {
            "value": "11",
            "name": "Promotional items:Misc"
          },
          "UnitPrice": 0.9090889,
          "Qty": 450,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 409.09,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 40.91,
      "TaxLine": [
        {
          "Amount": 40.91,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 409.09
          }
        }
      ]
    },
    "RecurDataRef": {
      "value": "13"
    },
    "CustomerRef": {
      "value": "8",
      "name": "Clement's Cleaners"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "8",
      "Line1": "101 River St",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.840717",
      "Long": "144.88504"
    },
    "ShipAddr": {
      "Id": "8",
      "Line1": "101 River St",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.840717",
      "Long": "144.88504"
    },
    "FreeFormAddress": true,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-05-14",
    "GlobalTaxCalculation": "TaxInclusive",
    "TotalAmt": 450.0,
    "HomeTotalAmt": 450.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 450.0,
    "HomeBalance": 450.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "165",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-11T14:51:57-08:00",
      "LastUpdatedTime": "2021-02-11T14:53:30-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1015",
    "TxnDate": "2021-02-12",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "167",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Name Badges",
        "Amount": 900.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "18",
            "name": "General services:Badges:Name Badges"
          },
          "ClassRef": {
            "value": "200200000000000016360",
            "name": "South"
          },
          "UnitPrice": 3,
          "Qty": 300,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "Water Bottles",
        "Amount": 3000.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "19",
            "name": "General services:Water Bottles"
          },
          "ClassRef": {
            "value": "200200000000000016360",
            "name": "South"
          },
          "UnitPrice": 10,
          "Qty": 300,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 3900.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 390.0,
      "TaxLine": [
        {
          "Amount": 390.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 3900.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "17",
      "name": "Jordan Burgess"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "15",
      "Line1": "1 Success Way",
      "City": "Sydney",
      "CountrySubDivisionCode": "NSW",
      "PostalCode": "2000",
      "Lat": "-33.871226",
      "Long": "150.897656"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-03-14",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 4290.0,
    "HomeTotalAmt": 4290.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "166",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-02-11T14:53:03-08:00",
      "LastUpdatedTime": "2021-02-11T14:53:03-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1016",
    "TxnDate": "2021-01-11",
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
        "Description": "Name Badges",
        "Amount": 900.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "18",
            "name": "General services:Badges:Name Badges"
          },
          "ClassRef": {
            "value": "200200000000000016360",
            "name": "South"
          },
          "UnitPrice": 3,
          "Qty": 300,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "Water Bottles",
        "Amount": 3000.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "19",
            "name": "General services:Water Bottles"
          },
          "ClassRef": {
            "value": "200200000000000016360",
            "name": "South"
          },
          "UnitPrice": 10,
          "Qty": 300,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 3900.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 390.0,
      "TaxLine": [
        {
          "Amount": 390.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 3900.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "17",
      "name": "Jordan Burgess"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "15",
      "Line1": "1 Success Way",
      "City": "Sydney",
      "CountrySubDivisionCode": "NSW",
      "PostalCode": "2000",
      "Lat": "-33.871226",
      "Long": "150.897656"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-02-10",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 4290.0,
    "HomeTotalAmt": 4290.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 4290.0,
    "HomeBalance": 4290.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "160",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-11T14:46:32-08:00",
      "LastUpdatedTime": "2021-02-11T14:49:12-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1012",
    "TxnDate": "2021-01-08",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "164",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "monthly consulting agreement",
        "Amount": 1200.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "1",
            "name": "General services:Services"
          },
          "ClassRef": {
            "value": "200200000000000016358",
            "name": "Administrative"
          },
          "UnitPrice": 150,
          "Qty": 8,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 1200.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 120.0,
      "TaxLine": [
        {
          "Amount": 120.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 1200.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "6",
      "name": "Charlie Whitehead"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "6",
      "Line1": "14 Kerr St",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.7964762",
      "Long": "144.9760071"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-02-07",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 1320.0,
    "HomeTotalAmt": 1320.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "162",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-02-11T14:47:54-08:00",
      "LastUpdatedTime": "2021-02-11T14:47:54-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1014",
    "TxnDate": "2021-01-11",
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
        "Description": "Name Badges",
        "Amount": 750.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "18",
            "name": "General services:Badges:Name Badges"
          },
          "ClassRef": {
            "value": "200200000000000016359",
            "name": "North"
          },
          "UnitPrice": 3,
          "Qty": 250,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "Water Bottles",
        "Amount": 2500.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "19",
            "name": "General services:Water Bottles"
          },
          "ClassRef": {
            "value": "200200000000000016359",
            "name": "North"
          },
          "UnitPrice": 10,
          "Qty": 250,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 3250.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 325.0,
      "TaxLine": [
        {
          "Amount": 325.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 3250.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "6",
      "name": "Charlie Whitehead"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "6",
      "Line1": "14 Kerr St",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.7964762",
      "Long": "144.9760071"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-02-10",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 3575.0,
    "HomeTotalAmt": 3575.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 3575.0,
    "HomeBalance": 3575.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "161",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-02-11T14:47:07-08:00",
      "LastUpdatedTime": "2021-02-11T14:47:07-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1013",
    "TxnDate": "2021-02-08",
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
        "Description": "monthly consulting agreement",
        "Amount": 1200.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "1",
            "name": "General services:Services"
          },
          "ClassRef": {
            "value": "200200000000000016358",
            "name": "Administrative"
          },
          "UnitPrice": 150,
          "Qty": 8,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 1200.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 120.0,
      "TaxLine": [
        {
          "Amount": 120.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 1200.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "6",
      "name": "Charlie Whitehead"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "6",
      "Line1": "14 Kerr St",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.7964762",
      "Long": "144.9760071"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-03-10",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 1320.0,
    "HomeTotalAmt": 1320.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 1320.0,
    "HomeBalance": 1320.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "128",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-08T12:35:25-08:00",
      "LastUpdatedTime": "2021-02-08T20:03:14-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1009",
    "TxnDate": "2020-11-08",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "146",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "consulting for upcoming company event",
        "Amount": 800.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "1",
            "name": "General services:Services"
          },
          "UnitPrice": 200,
          "Qty": 4,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 800.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 80.0,
      "TaxLine": [
        {
          "Amount": 80.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 800.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "3",
      "name": "Benjamin Yeung"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "4",
      "Line1": "9 Temple St",
      "City": "Melbourne,",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.869967",
      "Long": "145.093636"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-12-08",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 880.0,
    "HomeTotalAmt": 880.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "126",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-08T12:32:57-08:00",
      "LastUpdatedTime": "2021-02-08T19:36:45-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1007",
    "TxnDate": "2020-10-28",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "87",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "88",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "89",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "91",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "138",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Promotional items - engraved padfolios",
        "Amount": 375.0,
        "LinkedTxn": [
          {
            "TxnId": "87",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-18",
          "ItemRef": {
            "value": "10",
            "name": "Promotional items:Pad folios"
          },
          "UnitPrice": 7.5,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 375.0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 50,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "100% markup for Promotional items - engraved padfolios",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "87",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-18",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "3",
        "LineNum": 3,
        "Description": "Promotional items -- pens engraved",
        "Amount": 200.0,
        "LinkedTxn": [
          {
            "TxnId": "88",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-18",
          "ItemRef": {
            "value": "12",
            "name": "Promotional items:Pens"
          },
          "UnitPrice": 4,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 200.0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 50,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "4",
        "LineNum": 4,
        "Description": "100% markup for Promotional items -- pens engraved",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "88",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-18",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "5",
        "LineNum": 5,
        "Description": "promotional items -- misc engraved items",
        "Amount": 166.5,
        "LinkedTxn": [
          {
            "TxnId": "89",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-18",
          "ItemRef": {
            "value": "11",
            "name": "Promotional items:Misc"
          },
          "UnitPrice": 3.33,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 166.5,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 50,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "6",
        "LineNum": 6,
        "Description": "100% markup for promotional items -- misc engraved items",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "89",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-18",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "7",
        "LineNum": 7,
        "Description": "Catering -- food & beverage",
        "Amount": 3750.0,
        "LinkedTxn": [
          {
            "TxnId": "91",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-28",
          "ItemRef": {
            "value": "3",
            "name": "General services:Catering"
          },
          "UnitPrice": 3750,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 3750.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "8",
        "LineNum": 8,
        "Description": "20% markup for Catering -- food & beverage",
        "Amount": 750.0,
        "LinkedTxn": [
          {
            "TxnId": "91",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-10-28",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 750.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 5241.5,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 524.15,
      "TaxLine": [
        {
          "Amount": 524.15,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 5241.5
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "13",
      "name": "Hazel Robinson"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "13",
      "Line1": "PO Box 699",
      "City": "Richmond",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3004",
      "Lat": "-37.809902",
      "Long": "144.992981"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-11-27",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 5765.65,
    "HomeTotalAmt": 5765.65,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "125",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-08T12:32:13-08:00",
      "LastUpdatedTime": "2021-02-08T19:36:33-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1006",
    "TxnDate": "2020-07-16",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "60",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "137",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Catering -- food & beverage",
        "Amount": 1225.0,
        "LinkedTxn": [
          {
            "TxnId": "60",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-07-16",
          "ItemRef": {
            "value": "3",
            "name": "General services:Catering"
          },
          "UnitPrice": 1225,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 1225.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "20% markup for Catering -- food & beverage",
        "Amount": 245.0,
        "LinkedTxn": [
          {
            "TxnId": "60",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-07-16",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 245.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 1470.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 147.0,
      "TaxLine": [
        {
          "Amount": 147.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 1470.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "11",
      "name": "Lew Plumbing"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "11",
      "Line1": "10 Piper St.",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.71003899999999",
      "Long": "144.96566"
    },
    "ShipAddr": {
      "Id": "11",
      "Line1": "10 Piper St.",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.71003899999999",
      "Long": "144.96566"
    },
    "FreeFormAddress": true,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-08-15",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 1617.0,
    "HomeTotalAmt": 1617.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "124",
    "SyncToken": "2",
    "MetaData": {
      "CreateTime": "2021-02-08T12:12:31-08:00",
      "LastUpdatedTime": "2021-02-08T19:36:14-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1005",
    "TxnDate": "2020-06-10",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "49",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "51",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "136",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Catering -- food & beverage",
        "Amount": 700.0,
        "LinkedTxn": [
          {
            "TxnId": "49",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-06-10",
          "ItemRef": {
            "value": "3",
            "name": "General services:Catering"
          },
          "UnitPrice": 700,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 700.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "20% markup for Catering -- food & beverage",
        "Amount": 140.0,
        "LinkedTxn": [
          {
            "TxnId": "49",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-06-10",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 140.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "3",
        "LineNum": 3,
        "Description": "Venue Rental",
        "Amount": 750.0,
        "LinkedTxn": [
          {
            "TxnId": "51",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-06-10",
          "ItemRef": {
            "value": "16",
            "name": "General services:Venue Rental"
          },
          "UnitPrice": 750,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 750.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "4",
        "LineNum": 4,
        "Description": "20% markup for Venue Rental",
        "Amount": 150.0,
        "LinkedTxn": [
          {
            "TxnId": "51",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-06-10",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 150.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 1740.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 174.0,
      "TaxLine": [
        {
          "Amount": 174.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 1740.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "3",
      "name": "Benjamin Yeung"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "4",
      "Line1": "9 Temple St",
      "City": "Melbourne,",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.869967",
      "Long": "145.093636"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-07-10",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 1914.0,
    "HomeTotalAmt": 1914.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "123",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-08T12:11:22-08:00",
      "LastUpdatedTime": "2021-02-08T19:35:54-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1004",
    "TxnDate": "2020-09-13",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "70",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "71",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "72",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "77",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "79",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "135",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Promotional items - engraved padfolios",
        "Amount": 375.0,
        "LinkedTxn": [
          {
            "TxnId": "70",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-08-30",
          "ItemRef": {
            "value": "10",
            "name": "Promotional items:Pad folios"
          },
          "UnitPrice": 7.5,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 375.0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 50,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "100% markup for Promotional items - engraved padfolios",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "70",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-08-30",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "3",
        "LineNum": 3,
        "Description": "Promotional items -- pens engraved",
        "Amount": 200.0,
        "LinkedTxn": [
          {
            "TxnId": "71",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-08-30",
          "ItemRef": {
            "value": "12",
            "name": "Promotional items:Pens"
          },
          "UnitPrice": 4,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 200.0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 50,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "4",
        "LineNum": 4,
        "Description": "100% markup for Promotional items -- pens engraved",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "71",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-08-30",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "5",
        "LineNum": 5,
        "Description": "promotional items -- misc engraved items",
        "Amount": 166.5,
        "LinkedTxn": [
          {
            "TxnId": "72",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-08-30",
          "ItemRef": {
            "value": "11",
            "name": "Promotional items:Misc"
          },
          "UnitPrice": 3.33,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 166.5,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 50,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "6",
        "LineNum": 6,
        "Description": "100% markup for promotional items -- misc engraved items",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "72",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-08-30",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "7",
        "LineNum": 7,
        "Description": "Catering -- food & beverage",
        "Amount": 3750.0,
        "LinkedTxn": [
          {
            "TxnId": "77",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-09-06",
          "ItemRef": {
            "value": "3",
            "name": "General services:Catering"
          },
          "UnitPrice": 3750,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 3750.0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "8",
        "LineNum": 8,
        "Description": "100% markup for Catering -- food & beverage",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "77",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-09-06",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "9",
        "LineNum": 9,
        "Description": "Venue Rental",
        "Amount": 1000.0,
        "LinkedTxn": [
          {
            "TxnId": "79",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-09-06",
          "ItemRef": {
            "value": "16",
            "name": "General services:Venue Rental"
          },
          "UnitPrice": 1000,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 1000.0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "10",
        "LineNum": 10,
        "Description": "100% markup for Venue Rental",
        "Amount": 0,
        "LinkedTxn": [
          {
            "TxnId": "79",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-09-06",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 0,
            "Percent": 100,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 5491.5,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 549.15,
      "TaxLine": [
        {
          "Amount": 549.15,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 5491.5
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "5",
      "name": "Cathy Quon"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "5",
      "Line1": "12 Bagot Pl",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "INVALID",
      "Long": "INVALID"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-10-13",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 6040.65,
    "HomeTotalAmt": 6040.65,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "121",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-08T12:04:38-08:00",
      "LastUpdatedTime": "2021-02-08T19:35:31-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1002",
    "TxnDate": "2020-11-08",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "116",
        "TxnType": "Estimate"
      },
      {
        "TxnId": "134",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Holiday party - gold level",
        "Amount": 3000.0,
        "LinkedTxn": [
          {
            "TxnId": "116",
            "TxnType": "Estimate"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "7",
            "name": "Holiday party:Gold party"
          },
          "UnitPrice": 3000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "Water Bottles",
        "Amount": 2000.0,
        "LinkedTxn": [
          {
            "TxnId": "116",
            "TxnType": "Estimate"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "19",
            "name": "General services:Water Bottles"
          },
          "UnitPrice": 10,
          "Qty": 200,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "3",
        "LineNum": 3,
        "Description": "Venue rental",
        "Amount": 5000.0,
        "LinkedTxn": [
          {
            "TxnId": "116",
            "TxnType": "Estimate"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "16",
            "name": "General services:Venue Rental"
          },
          "UnitPrice": 5000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 10000.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 1000.0,
      "TaxLine": [
        {
          "Amount": 1000.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 10000.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "2",
      "name": "Oxon Insurance Agency"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "55",
      "Line1": "Oxon -- Holiday Party",
      "Line2": "11 Chain Road",
      "Line3": "Melbourne VIC  3001",
      "Lat": "INVALID",
      "Long": "INVALID"
    },
    "ShipAddr": {
      "Id": "3",
      "Line1": "11 Chain Road",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-38.066621",
      "Long": "145.297772"
    },
    "FreeFormAddress": true,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-12-08",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 11000.0,
    "HomeTotalAmt": 11000.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "122",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-08T12:06:46-08:00",
      "LastUpdatedTime": "2021-02-08T19:34:36-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1003",
    "TxnDate": "2020-05-01",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "117",
        "TxnType": "Estimate"
      },
      {
        "TxnId": "133",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Holiday party - gold level",
        "Amount": 2000.0,
        "LinkedTxn": [
          {
            "TxnId": "117",
            "TxnType": "Estimate"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "7",
            "name": "Holiday party:Gold party"
          },
          "UnitPrice": 2000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "Venue rental",
        "Amount": 10000.0,
        "LinkedTxn": [
          {
            "TxnId": "117",
            "TxnType": "Estimate"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "16",
            "name": "General services:Venue Rental"
          },
          "UnitPrice": 10000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "3",
        "LineNum": 3,
        "Description": "promotional items -- misc engraved items",
        "Amount": 1025.0,
        "LinkedTxn": [
          {
            "TxnId": "117",
            "TxnType": "Estimate"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "11",
            "name": "Promotional items:Misc"
          },
          "UnitPrice": 10.25,
          "Qty": 100,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 13025.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 1302.5,
      "TaxLine": [
        {
          "Amount": 1302.5,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 13025.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "9",
      "name": "Ecker Designs"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "9",
      "Line1": "Locked Bag 101",
      "City": "Sydney",
      "CountrySubDivisionCode": "NSW",
      "PostalCode": "2001",
      "Lat": "INVALID",
      "Long": "INVALID"
    },
    "ShipAddr": {
      "Id": "9",
      "Line1": "Locked Bag 101",
      "City": "Sydney",
      "CountrySubDivisionCode": "NSW",
      "PostalCode": "2001",
      "Lat": "INVALID",
      "Long": "INVALID"
    },
    "FreeFormAddress": true,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-05-31",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 14327.5,
    "HomeTotalAmt": 14327.5,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "115",
    "SyncToken": "1",
    "MetaData": {
      "CreateTime": "2021-02-08T11:45:50-08:00",
      "LastUpdatedTime": "2021-02-08T19:34:07-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1001",
    "TxnDate": "2020-05-01",
    "CurrencyRef": {
      "value": "AUD",
      "name": "Australian Dollar"
    },
    "ExchangeRate": 1,
    "LinkedTxn": [
      {
        "TxnId": "64",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "62",
        "TxnType": "ReimburseCharge"
      },
      {
        "TxnId": "132",
        "TxnType": "Payment"
      }
    ],
    "Line": [
      {
        "Id": "1",
        "LineNum": 1,
        "Description": "Catering -- food & beverage",
        "Amount": 1225.0,
        "LinkedTxn": [
          {
            "TxnId": "62",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-07-30",
          "ItemRef": {
            "value": "3",
            "name": "General services:Catering"
          },
          "UnitPrice": 1225,
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 1225.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "20% markup for Catering -- food & beverage",
        "Amount": 245.0,
        "LinkedTxn": [
          {
            "TxnId": "62",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "2"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-07-30",
          "MarkupInfo": {
            "PercentBased": true,
            "Value": 245.0,
            "Percent": 20,
            "MarkUpIncomeAccountRef": {
              "value": "68",
              "name": "Markup"
            }
          },
          "ItemAccountRef": {
            "value": "68",
            "name": "Markup"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "3",
        "LineNum": 3,
        "Description": "Venue Rental",
        "Amount": 1200.0,
        "LinkedTxn": [
          {
            "TxnId": "64",
            "TxnType": "ReimburseCharge",
            "TxnLineId": "1"
          }
        ],
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ServiceDate": "2020-07-30",
          "ItemRef": {
            "value": "16",
            "name": "General services:Venue Rental"
          },
          "UnitPrice": 1200,
          "MarkupInfo": {
            "PercentBased": false,
            "Value": 1200.0,
            "MarkUpIncomeAccountRef": {
              "value": "75",
              "name": "Billable Expenses Income"
            }
          },
          "Qty": 1,
          "ItemAccountRef": {
            "value": "75",
            "name": "Billable Expenses Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "4",
        "LineNum": 4,
        "Description": "Holiday party - platinum level",
        "Amount": 3000.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "8",
            "name": "Holiday party:Platinum party"
          },
          "UnitPrice": 3000,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 5670.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 567.0,
      "TaxLine": [
        {
          "Amount": 567.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 5670.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "2",
      "name": "Oxon Insurance Agency"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "3",
      "Line1": "11 Chain Road",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-38.066621",
      "Long": "145.297772"
    },
    "ShipAddr": {
      "Id": "3",
      "Line1": "11 Chain Road",
      "City": "Melbourne",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-38.066621",
      "Long": "145.297772"
    },
    "FreeFormAddress": true,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-05-31",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 6237.0,
    "HomeTotalAmt": 6237.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 0,
    "HomeBalance": 0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "130",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-02-08T12:36:42-08:00",
      "LastUpdatedTime": "2021-02-08T12:36:42-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1011",
    "TxnDate": "2020-12-08",
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
        "Description": "Name Badges",
        "Amount": 1500.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "18",
            "name": "General services:Badges:Name Badges"
          },
          "UnitPrice": 3,
          "Qty": 500,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 1500.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 150.0,
      "TaxLine": [
        {
          "Amount": 150.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 1500.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "1",
      "name": "Adwin Ko"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "2",
      "Line1": "25 Stone Drive",
      "City": "Sydney",
      "CountrySubDivisionCode": "NSW",
      "PostalCode": "2001",
      "Lat": "-33.737492",
      "Long": "151.195909"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-01-07",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 1650.0,
    "HomeTotalAmt": 1650.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 1650.0,
    "HomeBalance": 1650.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "129",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-02-08T12:36:12-08:00",
      "LastUpdatedTime": "2021-02-08T12:36:12-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1010",
    "TxnDate": "2020-12-08",
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
        "Description": "consult on event options and considerations",
        "Amount": 400.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "1",
            "name": "General services:Services"
          },
          "UnitPrice": 400,
          "Qty": 1,
          "ItemAccountRef": {
            "value": "1",
            "name": "Services"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 400.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 40.0,
      "TaxLine": [
        {
          "Amount": 40.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 400.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "17",
      "name": "Jordan Burgess"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "15",
      "Line1": "1 Success Way",
      "City": "Sydney",
      "CountrySubDivisionCode": "NSW",
      "PostalCode": "2000",
      "Lat": "-33.871226",
      "Long": "150.897656"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2021-01-07",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 440.0,
    "HomeTotalAmt": 440.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 440.0,
    "HomeBalance": 440.0
  },
  {
    "AllowIPNPayment": false,
    "AllowOnlinePayment": false,
    "AllowOnlineCreditCardPayment": false,
    "AllowOnlineACHPayment": false,
    "domain": "QBO",
    "sparse": false,
    "Id": "127",
    "SyncToken": "0",
    "MetaData": {
      "CreateTime": "2021-02-08T12:34:31-08:00",
      "LastUpdatedTime": "2021-02-08T12:34:31-08:00"
    },
    "CustomField": [
      {
        "DefinitionId": "1",
        "Name": "Event Rep",
        "Type": "StringType"
      }
    ],
    "DocNumber": "1008",
    "TxnDate": "2020-11-08",
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
        "Description": "Name Badges",
        "Amount": 1500.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "18",
            "name": "General services:Badges:Name Badges"
          },
          "UnitPrice": 3,
          "Qty": 500,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Id": "2",
        "LineNum": 2,
        "Description": "Water Bottles",
        "Amount": 5000.0,
        "DetailType": "SalesItemLineDetail",
        "SalesItemLineDetail": {
          "ItemRef": {
            "value": "19",
            "name": "General services:Water Bottles"
          },
          "UnitPrice": 10,
          "Qty": 500,
          "ItemAccountRef": {
            "value": "81",
            "name": "Sales of Product Income"
          },
          "TaxCodeRef": {
            "value": "10"
          }
        }
      },
      {
        "Amount": 6500.0,
        "DetailType": "SubTotalLineDetail",
        "SubTotalLineDetail": {}
      }
    ],
    "TxnTaxDetail": {
      "TotalTax": 650.0,
      "TaxLine": [
        {
          "Amount": 650.0,
          "DetailType": "TaxLineDetail",
          "TaxLineDetail": {
            "TaxRateRef": {
              "value": "20"
            },
            "PercentBased": true,
            "TaxPercent": 10,
            "NetAmountTaxable": 6500.0
          }
        }
      ]
    },
    "CustomerRef": {
      "value": "3",
      "name": "Benjamin Yeung"
    },
    "CustomerMemo": {
      "value": "Thanks for your business!  We appreciate referrals!"
    },
    "BillAddr": {
      "Id": "4",
      "Line1": "9 Temple St",
      "City": "Melbourne,",
      "CountrySubDivisionCode": "VIC",
      "PostalCode": "3001",
      "Lat": "-37.869967",
      "Long": "145.093636"
    },
    "FreeFormAddress": false,
    "SalesTermRef": {
      "value": "3"
    },
    "DueDate": "2020-12-08",
    "GlobalTaxCalculation": "TaxExcluded",
    "TotalAmt": 7150.0,
    "HomeTotalAmt": 7150.0,
    "PrintStatus": "NotSet",
    "EmailStatus": "NotSet",
    "Balance": 7150.0,
    "HomeBalance": 7150.0
  }
]
""".trimIndent()