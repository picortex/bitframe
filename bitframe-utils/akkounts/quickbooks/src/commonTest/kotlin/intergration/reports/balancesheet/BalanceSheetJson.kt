package intergration.reports.balancesheet

val BALANCE_SHEET_JSON = """
{
  "Header": {
    "Time": "2021-06-10T00:08:07-07:00",
    "ReportName": "BalanceSheet",
    "ReportBasis": "Accrual",
    "StartPeriod": "2021-06-09",
    "EndPeriod": "2021-06-10",
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
              "value": "Assets"
            },
            {
              "value": ""
            }
          ]
        },
        "Rows": {
          "Row": [
            {
              "Header": {
                "ColData": [
                  {
                    "value": "Current Assets"
                  },
                  {
                    "value": ""
                  }
                ]
              },
              "Rows": {
                "Row": [
                  {
                    "Header": {
                      "ColData": [
                        {
                          "value": "Accounts receivable"
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
                              "value": "Accounts Receivable (A/R)",
                              "id": "95"
                            },
                            {
                              "value": "25475.00"
                            }
                          ],
                          "type": "Data"
                        }
                      ]
                    },
                    "Summary": {
                      "ColData": [
                        {
                          "value": "Total Accounts receivable"
                        },
                        {
                          "value": "25475.00"
                        }
                      ]
                    },
                    "type": "Section",
                    "group": "AssetsReceivable"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Cash and cash equivalents",
                        "id": "13"
                      },
                      {
                        "value": "46087.46"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Westpac - EveryBusiness",
                        "id": "105"
                      },
                      {
                        "value": "-1375.00"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Inventory Asset",
                        "id": "87"
                      },
                      {
                        "value": "7725.00"
                      }
                    ],
                    "type": "Data"
                  }
                ]
              },
              "Summary": {
                "ColData": [
                  {
                    "value": "Total Current Assets"
                  },
                  {
                    "value": "77912.46"
                  }
                ]
              },
              "type": "Section",
              "group": "TotalAssetLessCurrentLiabilities"
            },
            {
              "Header": {
                "ColData": [
                  {
                    "value": "Long-term assets"
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
                        "value": "Accumulated depreciation on property, plant and equipment",
                        "id": "6"
                      },
                      {
                        "value": "-399.96"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Furniture and Equipment",
                        "id": "78"
                      },
                      {
                        "value": "2750.00"
                      }
                    ],
                    "type": "Data"
                  }
                ]
              },
              "Summary": {
                "ColData": [
                  {
                    "value": "Total long-term assets"
                  },
                  {
                    "value": "2350.04"
                  }
                ]
              },
              "type": "Section",
              "group": "TotalLongTermAssets"
            }
          ]
        },
        "Summary": {
          "ColData": [
            {
              "value": "Total Assets"
            },
            {
              "value": "80262.50"
            }
          ]
        },
        "type": "Section",
        "group": "NetAssets"
      },
      {
        "Header": {
          "ColData": [
            {
              "value": "Liabilities and shareholder's equity"
            },
            {
              "value": ""
            }
          ]
        },
        "Rows": {
          "Row": [
            {
              "Header": {
                "ColData": [
                  {
                    "value": "Current liabilities:"
                  },
                  {
                    "value": ""
                  }
                ]
              },
              "Rows": {
                "Row": [
                  {
                    "Header": {
                      "ColData": [
                        {
                          "value": "Accounts payable"
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
                              "value": "Accounts Payable (A/P)",
                              "id": "92"
                            },
                            {
                              "value": "433.15"
                            }
                          ],
                          "type": "Data"
                        },
                        {
                          "ColData": [
                            {
                              "value": "Accounts Payable (A/P) - HKD",
                              "id": "91"
                            },
                            {
                              "value": "0.00"
                            }
                          ],
                          "type": "Data"
                        }
                      ]
                    },
                    "Summary": {
                      "ColData": [
                        {
                          "value": "Total Accounts payable"
                        },
                        {
                          "value": "433.15"
                        }
                      ]
                    },
                    "type": "Section",
                    "group": "AccountsPayable"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Visa Credit Card",
                        "id": "86"
                      },
                      {
                        "value": "3370.60"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "BAS Liabilities Payable",
                        "id": "71"
                      },
                      {
                        "value": "810.34"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "BAS Suspense",
                        "id": "72"
                      },
                      {
                        "value": "-740.83"
                      }
                    ],
                    "type": "Data"
                  }
                ]
              },
              "Summary": {
                "ColData": [
                  {
                    "value": "Total current liabilities"
                  },
                  {
                    "value": "3873.26"
                  }
                ]
              },
              "type": "Section",
              "group": "TotalLongTermLiabilities"
            },
            {
              "Header": {
                "ColData": [
                  {
                    "value": "Non-current liabilities:"
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
                        "value": "Note Payable",
                        "id": "79"
                      },
                      {
                        "value": "45102.12"
                      }
                    ],
                    "type": "Data"
                  }
                ]
              },
              "Summary": {
                "ColData": [
                  {
                    "value": "Total non-current liabilities"
                  },
                  {
                    "value": "45102.12"
                  }
                ]
              },
              "type": "Section",
              "group": "TotalNonCurLiabilities"
            },
            {
              "Header": {
                "ColData": [
                  {
                    "value": "Shareholders' equity:"
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
                        "value": "Net Income"
                      },
                      {
                        "value": "-3989.68"
                      }
                    ],
                    "type": "Data",
                    "group": "NetIncome"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Opening Balance Equity",
                        "id": "90"
                      },
                      {
                        "value": "8918.20"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Owner's Contribution",
                        "id": "97"
                      },
                      {
                        "value": "10000.00"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Owner's Drawings",
                        "id": "96"
                      },
                      {
                        "value": "-2000.00"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Retained Earnings",
                        "id": "2"
                      },
                      {
                        "value": "5608.60"
                      }
                    ],
                    "type": "Data"
                  },
                  {
                    "ColData": [
                      {
                        "value": "Share capital",
                        "id": "57"
                      },
                      {
                        "value": "12750.00"
                      }
                    ],
                    "type": "Data"
                  }
                ]
              },
              "Summary": {
                "ColData": [
                  {
                    "value": "Total shareholders' equity"
                  },
                  {
                    "value": "31287.12"
                  }
                ]
              },
              "type": "Section",
              "group": "TotalShareHoldersEquityNode"
            }
          ]
        },
        "Summary": {
          "ColData": [
            {
              "value": "Total liabilities and equity"
            },
            {
              "value": "80262.50"
            }
          ]
        },
        "type": "Section",
        "group": "NetLiabilitiesAndShareHolderEquity"
      }
    ]
  }
}    
""".trimIndent()