package intergration.reports.incomestatement

val INCOME_STATEMENT_JSON = """
{
  "Header": {
    "Time": "2021-06-07T19:15:08-07:00",
    "ReportName": "ProfitAndLoss",
    "ReportBasis": "Accrual",
    "StartPeriod": "2020-07-01",
    "EndPeriod": "2021-06-08",
    "SummarizeColumnsBy": "Total",
    "Currency": "AUD",
    "Option": [
      {
        "Name": "AccountingStandard",
        "Value": "PE"
      },
      {
        "Name": "NoReportData",
        "Value": "false"
      }
    ]
  },
  "Columns": {
    "Column": [
      {
        "ColTitle": "",
        "ColType": "Account",
        "MetaData": [
          {
            "Name": "ColKey",
            "Value": "account"
          }
        ]
      },
      {
        "ColTitle": "Total",
        "ColType": "Money",
        "MetaData": [
          {
            "Name": "ColKey",
            "Value": "total"
          }
        ]
      }
    ]
  },
  "Rows": {
    "Row": [
      {
        "Header": {
          "ColData": [
            {
              "value": "Income"
            },
            {
              "value": ""
            }
          ]
        },
        "Rows": {
          "Row": [
            {
              "ColData": [
                {
                  "value": "Billable Expenses Income",
                  "id": "75"
                },
                {
                  "value": "18253.45"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Markup",
                  "id": "68"
                },
                {
                  "value": "995.00"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Sales of Product Income",
                  "id": "81"
                },
                {
                  "value": "21050.00"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Services",
                  "id": "1"
                },
                {
                  "value": "14872.72"
                }
              ],
              "type": "Data"
            }
          ]
        },
        "Summary": {
          "ColData": [
            {
              "value": "Total Income"
            },
            {
              "value": "55171.17"
            }
          ]
        },
        "type": "Section",
        "group": "Income"
      },
      {
        "Header": {
          "ColData": [
            {
              "value": "Cost of Sales"
            },
            {
              "value": ""
            }
          ]
        },
        "Rows": {
          "Row": [
            {
              "ColData": [
                {
                  "value": "Cost of sales",
                  "id": "88"
                },
                {
                  "value": "10543.48"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Cost of Sales - billable expenses",
                  "id": "76"
                },
                {
                  "value": "30095.25"
                }
              ],
              "type": "Data"
            }
          ]
        },
        "Summary": {
          "ColData": [
            {
              "value": "Total Cost of Sales"
            },
            {
              "value": "40638.73"
            }
          ]
        },
        "type": "Section",
        "group": "COGS"
      },
      {
        "Summary": {
          "ColData": [
            {
              "value": "Gross Profit"
            },
            {
              "value": "14532.44"
            }
          ]
        },
        "type": "Section",
        "group": "GrossProfit"
      },
      {
        "Header": {
          "ColData": [
            {
              "value": "Expenses"
            },
            {
              "value": ""
            }
          ]
        },
        "Rows": {
          "Row": [
            {
              "ColData": [
                {
                  "value": "Amortisation (and depreciation) expense",
                  "id": "8"
                },
                {
                  "value": "299.97"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Insurance - General",
                  "id": "27"
                },
                {
                  "value": "1500.00"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Interest expense",
                  "id": "30"
                },
                {
                  "value": "855.00"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Janitorial Expense (deleted)",
                  "id": "93"
                },
                {
                  "value": "423.45"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Rent or lease payments",
                  "id": "52"
                },
                {
                  "value": "12500.00"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Shipping and delivery expense",
                  "id": "58"
                },
                {
                  "value": "266.36"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Utilities - Electric & Gas",
                  "id": "83"
                },
                {
                  "value": "1889.92"
                }
              ],
              "type": "Data"
            },
            {
              "ColData": [
                {
                  "value": "Utilities - Water",
                  "id": "84"
                },
                {
                  "value": "766.51"
                }
              ],
              "type": "Data"
            }
          ]
        },
        "Summary": {
          "ColData": [
            {
              "value": "Total Expenses"
            },
            {
              "value": "18501.21"
            }
          ]
        },
        "type": "Section",
        "group": "Expenses"
      },
      {
        "Summary": {
          "ColData": [
            {
              "value": "Net Earnings"
            },
            {
              "value": "-3968.77"
            }
          ]
        },
        "type": "Section",
        "group": "NetIncome"
      }
    ]
  }
}    
""".trimIndent()