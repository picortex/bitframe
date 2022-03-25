val response = """{
  "reports": [
    {
      "imageUrl": null,
      "singleValue": 0,
      "singleValueString": "0",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": null,
        "stacked": false,
        "dataClass": "Quotation",
        "dateControls": null,
        "description": "Quotations this Month",
        "reportType": "SINGLE_VALUE",
        "numberFormat": "#,###",
        "money": null,
        "name": "Quotations this Month",
        "chartType": null,
        "reportStyle": null,
        "id": 10002,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "imageUrl": null,
      "singleValue": 0,
      "singleValueString": "0",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": null,
        "stacked": false,
        "dataClass": "Job",
        "dateControls": null,
        "description": "Jobs this Month",
        "reportType": "SINGLE_VALUE",
        "numberFormat": "#,###",
        "money": null,
        "name": "Jobs this Month",
        "chartType": null,
        "reportStyle": null,
        "id": 10003,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "imageUrl": "https:\/\/quickchart.io\/chart?c=%7B%22data%22%3A%7B%22datasets%22%3A%5B%7B%22pointBorderColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C7590.00%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Revenue%22%7D%2C%7B%22pointBorderColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Expenses%22%7D%5D%2C%22labels%22%3A%5B%222022+Jan%22%2C%222022+Feb%22%5D%7D%2C%22type%22%3A%22bar%22%7D",
      "datasets": [
        {
          "pointBorderColor": "rgba(62,149,205, 1)",
          "backgroundColor": "rgba(62,149,205, 1)",
          "borderColor": "rgba(255,255,255,1)",
          "data": {
            "2022 Jan": 0,
            "2022 Feb": 7590.00
          },
          "borderWidth": "1",
          "label": "Revenue"
        },
        {
          "pointBorderColor": "rgba(60,186,159, 1)",
          "backgroundColor": "rgba(60,186,159, 1)",
          "borderColor": "rgba(255,255,255,1)",
          "data": {
            "2022 Jan": 0
          },
          "borderWidth": "1",
          "label": "Expenses"
        }
      ],
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": null,
        "stacked": false,
        "dataClass": null,
        "dateControls": null,
        "description": "Revenue VS Expenses",
        "reportType": "REPARTITION",
        "numberFormat": null,
        "money": true,
        "name": "Revenue VS Expenses",
        "chartType": "bar",
        "reportStyle": null,
        "id": 10007,
        "splitByField": null,
        "gridWidth": 2,
        "category": "OVERVIEW"
      },
      "labels": [
        "2022 Jan",
        "2022 Feb"
      ]
    },
    {
      "imageUrl": null,
      "singleValue": 0,
      "singleValueString": "Sh 0",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": "total",
        "stacked": false,
        "dataClass": "Invoice",
        "dateControls": null,
        "description": "Revenue this Month",
        "reportType": "SINGLE_VALUE",
        "numberFormat": null,
        "money": true,
        "name": "Revenue this Month",
        "chartType": null,
        "reportStyle": null,
        "id": 10008,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OVERVIEW"
      },
      "labels": null
    },
    {
      "imageUrl": null,
      "singleValue": 0,
      "singleValueString": "Sh 0",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": "amount",
        "stacked": false,
        "dataClass": "Expense",
        "dateControls": null,
        "description": "Expenses this Month",
        "reportType": "SINGLE_VALUE",
        "numberFormat": null,
        "money": true,
        "name": "Expenses this Month",
        "chartType": null,
        "reportStyle": null,
        "id": 10009,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OVERVIEW"
      },
      "labels": null
    },
    {
      "imageUrl": null,
      "singleValue": 1,
      "singleValueString": "1",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": null,
        "stacked": false,
        "dataClass": "TeamMember",
        "dateControls": null,
        "description": "Number of Employees",
        "reportType": "SINGLE_VALUE",
        "numberFormat": "#,###",
        "money": null,
        "name": "Employees",
        "chartType": null,
        "reportStyle": null,
        "id": 10010,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "imageUrl": null,
      "singleValue": 1,
      "singleValueString": "1",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": null,
        "stacked": false,
        "dataClass": "Customer",
        "dateControls": null,
        "description": "Number of Clients",
        "reportType": "SINGLE_VALUE",
        "numberFormat": "#,###",
        "money": null,
        "name": "Clients",
        "chartType": null,
        "reportStyle": null,
        "id": 10011,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "locationData": [],
      "imageUrl": null,
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": "location",
        "stacked": false,
        "dataClass": "Customer",
        "dateControls": null,
        "description": null,
        "reportType": "LOCATION",
        "numberFormat": "R #,###,###,##0.00%",
        "money": null,
        "name": "Customers by Location",
        "chartType": null,
        "reportStyle": null,
        "id": 10012,
        "splitByField": null,
        "gridWidth": 2,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "dim2Keys": [
        "All"
      ],
      "imageUrl": null,
      "tabularData": {},
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": null,
        "stacked": false,
        "dataClass": "Job",
        "dateControls": null,
        "description": "Jobs per Client",
        "reportType": "REPARTITION",
        "numberFormat": "#,###",
        "money": null,
        "name": "Jobs per Client",
        "chartType": "bar",
        "reportStyle": "TABULAR",
        "id": 10013,
        "splitByField": "customer",
        "gridWidth": 2,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "dim2Keys": [
        "All"
      ],
      "imageUrl": null,
      "tabularData": {
        "John": {
          "All": 1
        }
      },
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": null,
        "stacked": false,
        "dataClass": "Requisition",
        "dateControls": null,
        "description": "Sales Orders per Client",
        "reportType": "REPARTITION",
        "numberFormat": "#,###",
        "money": null,
        "name": "Sales Orders per Client",
        "chartType": "bar",
        "reportStyle": "TABULAR",
        "id": 10014,
        "splitByField": "customer",
        "gridWidth": 2,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "dim2Keys": [
        "All"
      ],
      "imageUrl": null,
      "tabularData": {},
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": "hours",
        "stacked": false,
        "dataClass": "Hours",
        "dateControls": null,
        "description": "Hours worked per employee",
        "reportType": "REPARTITION",
        "numberFormat": "#,###",
        "money": null,
        "name": "Hours per Employee",
        "chartType": "bar",
        "reportStyle": "TABULAR",
        "id": 10015,
        "splitByField": "teamMember",
        "gridWidth": 2,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "dim2Keys": [
        "All"
      ],
      "imageUrl": null,
      "tabularData": {
        "John": {
          "All": 7590.00
        }
      },
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": "total",
        "stacked": false,
        "dataClass": "Invoice",
        "dateControls": null,
        "description": "Revenue per Client",
        "reportType": "REPARTITION",
        "numberFormat": "#,###",
        "money": null,
        "name": "Revenue per Client",
        "chartType": "bar",
        "reportStyle": "TABULAR",
        "id": 10016,
        "splitByField": "customer",
        "gridWidth": 2,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "dim2Keys": [
        "All"
      ],
      "imageUrl": null,
      "tabularData": {
        "Computer": {
          "All": 3.00
        }
      },
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": "quantity",
        "stacked": false,
        "dataClass": "ProductItem",
        "dateControls": null,
        "description": "Products Sold",
        "reportType": "REPARTITION",
        "numberFormat": null,
        "money": null,
        "name": "Products Sold",
        "chartType": null,
        "reportStyle": "TABULAR",
        "id": 10017,
        "splitByField": "product",
        "gridWidth": 4,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "imageUrl": "https:\/\/quickchart.io\/chart?c=%7B%22data%22%3A%7B%22datasets%22%3A%5B%7B%22pointBorderColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C3.00%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Products+Sold+Over+Time%22%7D%5D%2C%22labels%22%3A%5B%222022+Jan%22%2C%222022+Feb%22%5D%7D%2C%22type%22%3A%22bar%22%7D",
      "datasets": [
        {
          "pointBorderColor": "rgba(62,149,205, 1)",
          "backgroundColor": "rgba(62,149,205, 1)",
          "borderColor": "rgba(255,255,255,1)",
          "data": {
            "2022 Jan": 0,
            "2022 Feb": 3.00
          },
          "borderWidth": "1",
          "label": "Products Sold Over Time"
        }
      ],
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 4,
        "dataField": "quantity",
        "stacked": true,
        "dataClass": "ProductItem",
        "dateControls": null,
        "description": "Products Sold Over Time",
        "reportType": "TIME_BASED",
        "numberFormat": null,
        "money": null,
        "name": "Products Sold Over Time",
        "chartType": "bar",
        "reportStyle": null,
        "id": 10018,
        "splitByField": null,
        "gridWidth": 2,
        "category": "OPERATIONAL"
      },
      "labels": [
        "2022 Jan",
        "2022 Feb"
      ]
    },
    {
      "imageUrl": null,
      "singleValue": 0.00,
      "singleValueString": "Sh 0",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": "amountOutstanding",
        "stacked": false,
        "dataClass": "Invoice",
        "dateControls": null,
        "description": "Outstanding Payments From Customers",
        "reportType": "SINGLE_VALUE",
        "numberFormat": null,
        "money": true,
        "name": "Outstanding Payments From Customers",
        "chartType": null,
        "reportStyle": null,
        "id": 10019,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OPERATIONAL"
      },
      "labels": null
    },
    {
      "imageUrl": null,
      "singleValue": 288.00,
      "singleValueString": "Sh 288",
      "datasets": null,
      "params": {
        "dateTo": "23\/02\/2022",
        "dateFrom": "24\/01\/2022"
      },
      "config": {
        "gridHeight": 2,
        "dataField": "amountOutstanding",
        "stacked": false,
        "dataClass": "Expense",
        "dateControls": null,
        "description": "Outstanding Payments To Suppliers",
        "reportType": "SINGLE_VALUE",
        "numberFormat": null,
        "money": true,
        "name": "Outstanding Payments To Suppliers",
        "chartType": null,
        "reportStyle": null,
        "id": 10020,
        "splitByField": null,
        "gridWidth": 1,
        "category": "OPERATIONAL"
      },
      "labels": null
    }
  ],
  "currency": "TZS",
  "status": "ok"
}"""