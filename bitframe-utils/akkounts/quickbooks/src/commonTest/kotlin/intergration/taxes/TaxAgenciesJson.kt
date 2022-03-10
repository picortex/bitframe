package intergration.taxes

val AGENCIES_JSON = """
{
  "QueryResponse": {
    "TaxAgency": [
      {
        "TaxTrackedOnPurchases": false,
        "TaxTrackedOnSales": true,
        "TaxAgencyConfig": "USER_DEFINED",
        "domain": "QBO",
        "sparse": false,
        "Id": "2",
        "SyncToken": "0",
        "MetaData": {
          "CreateTime": "2021-06-15T05:02:15-07:00",
          "LastUpdatedTime": "2021-06-15T05:02:15-07:00"
        },
        "DisplayName": "AndersonRevenueAuthority"
      },
      {
        "TaxTrackedOnPurchases": false,
        "TaxTrackedOnSales": true,
        "TaxAgencyConfig": "USER_DEFINED",
        "domain": "QBO",
        "sparse": false,
        "Id": "3",
        "SyncToken": "0",
        "MetaData": {
          "CreateTime": "2021-06-17T19:14:48-07:00",
          "LastUpdatedTime": "2021-06-17T19:14:48-07:00"
        },
        "DisplayName": "AndersonRevenueAuthority-1623982486314"
      },
      {
        "TaxTrackedOnPurchases": false,
        "TaxTrackedOnSales": true,
        "TaxAgencyConfig": "USER_DEFINED",
        "domain": "QBO",
        "sparse": false,
        "Id": "4",
        "SyncToken": "0",
        "MetaData": {
          "CreateTime": "2021-06-17T19:18:18-07:00",
          "LastUpdatedTime": "2021-06-17T19:18:18-07:00"
        },
        "DisplayName": "AndersonRevenueAuthority-1623982696383"
      },
      {
        "TaxRegistrationNumber": "99-1234567",
        "TaxTrackedOnPurchases": true,
        "TaxTrackedOnSales": true,
        "LastFileDate": "2020-08-28",
        "TaxAgencyConfig": "SYSTEM_GENERATED",
        "domain": "QBO",
        "sparse": false,
        "Id": "1",
        "SyncToken": "2",
        "MetaData": {
          "CreateTime": "2021-01-10T19:52:52-08:00",
          "LastUpdatedTime": "2021-02-08T20:01:46-08:00"
        },
        "DisplayName": "Australian Tax Office"
      }
    ],
    "startPosition": 1,
    "maxResults": 4,
    "totalCount": 4
  },
  "time": "2021-06-17T19:18:21.355-07:00"
}    
""".trimIndent()