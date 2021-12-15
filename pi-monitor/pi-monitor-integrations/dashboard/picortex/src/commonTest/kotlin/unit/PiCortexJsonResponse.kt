package unit

val PiCortexJsonResponse by lazy {
    """
        {
           "reports":[
              {
                 "imageUrl":"https:\/\/quickchart.io\/chart?c=%7B%22data%22%3A%7B%22datasets%22%3A%5B%7B%22pointBorderColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B1%2C0%2C3%2C0%2C0%2C0%2C6%2C0%2C1%2C1%2C0%2C1%2C7%2C1%2C1%2C0%2C0%2C2%2C9%2C3%2C8%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Sales+Order%22%7D%5D%2C%22labels%22%3A%5B%222020+Apr%22%2C%222020+May%22%2C%222020+Jun%22%2C%222020+Jul%22%2C%222020+Aug%22%2C%222020+Sep%22%2C%222020+Oct%22%2C%222020+Nov%22%2C%222020+Dec%22%2C%222021+Jan%22%2C%222021+Feb%22%2C%222021+Mar%22%2C%222021+Apr%22%2C%222021+May%22%2C%222021+Jun%22%2C%222021+Jul%22%2C%222021+Aug%22%2C%222021+Sep%22%2C%222021+Oct%22%2C%222021+Nov%22%2C%222021+Dec%22%5D%7D%2C%22type%22%3A%22bar%22%7D",
                 "datasets":[
                    {
                       "pointBorderColor":"rgba(62,149,205, 1)",
                       "backgroundColor":"rgba(62,149,205, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2020 Apr":1,
                          "2020 May":0,
                          "2020 Jun":3,
                          "2020 Jul":0,
                          "2020 Aug":0,
                          "2020 Sep":0,
                          "2020 Oct":6,
                          "2020 Nov":0,
                          "2020 Dec":1,
                          "2021 Jan":1,
                          "2021 Feb":0,
                          "2021 Mar":1,
                          "2021 Apr":7,
                          "2021 May":1,
                          "2021 Jun":1,
                          "2021 Jul":0,
                          "2021 Aug":0,
                          "2021 Sep":2,
                          "2021 Oct":9,
                          "2021 Nov":3,
                          "2021 Dec":8
                       },
                       "borderWidth":"1",
                       "label":"Sales Order"
                    }
                 ],
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":"total",
                    "stacked":false,
                    "dataClass":"Requisition",
                    "dateControls":true,
                    "description":"Sales Order Count",
                    "reportType":"TIME_BASED",
                    "numberFormat":"###,###.### '%'",
                    "money":false,
                    "name":"Sales Order",
                    "chartType":"bar",
                    "reportStyle":null,
                    "id":10063,
                    "splitByField":null,
                    "gridWidth":2
                 },
                 "labels":[
                    "2020 Apr",
                    "2020 May",
                    "2020 Jun",
                    "2020 Jul",
                    "2020 Aug",
                    "2020 Sep",
                    "2020 Oct",
                    "2020 Nov",
                    "2020 Dec",
                    "2021 Jan",
                    "2021 Feb",
                    "2021 Mar",
                    "2021 Apr",
                    "2021 May",
                    "2021 Jun",
                    "2021 Jul",
                    "2021 Aug",
                    "2021 Sep",
                    "2021 Oct",
                    "2021 Nov",
                    "2021 Dec"
                 ]
              },
              {
                 "dim2Keys":[
                    "All"
                 ],
                 "imageUrl":null,
                 "tabularData":{
                    "Joe Exotica":{
                       "All":4703.50
                    },
                    "James Dean Inc.":{
                       "All":21794.11
                    },
                    "Caroline Baskin":{
                       "All":55200.00
                    },
                    "George Sechu":{
                       "All":190596.40
                    },
                    "Steven":{
                       "All":9361.90
                    },
                    "Viator.com":{
                       "All":66407.84
                    },
                    "GetYourGuide":{
                       "All":3439.01
                    },
                    "Company ABC":{
                       "All":4542.50
                    },
                    "JD Enterprises":{
                       "All":4432.50
                    }
                 },
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":"total",
                    "stacked":false,
                    "dataClass":"Invoice",
                    "dateControls":null,
                    "description":"Revenue per Client",
                    "reportType":"REPARTITION",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Revenue per Client",
                    "chartType":"bar",
                    "reportStyle":"TABULAR",
                    "id":10061,
                    "splitByField":"customer",
                    "gridWidth":2
                 },
                 "labels":null
              },
              {
                 "imageUrl":"https:\/\/quickchart.io\/chart?c=%7B%22data%22%3A%7B%22datasets%22%3A%5B%7B%22pointBorderColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C8625.00%2C55200.00%2C161.00%2C4874.16%2C1323.65%2C2351.75%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Revenue%22%7D%2C%7B%22pointBorderColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C0%2C0%2C0%2C2268.00%2C864.00%2C1365.55%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Expenses%22%7D%2C%7B%22pointBorderColor%22%3A%22rgba%28196%2C88%2C80%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%28196%2C88%2C80%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C8625.00%2C55200.00%2C161.00%2C4874.16%2C1323.65%2C2351.75%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Revenue%22%7D%2C%7B%22pointBorderColor%22%3A%22rgba%28142%2C94%2C162%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%28142%2C94%2C162%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C0%2C0%2C0%2C2268.00%2C864.00%2C1365.55%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Expenses%22%7D%5D%2C%22labels%22%3A%5B%222021+Jun%22%2C%222021+Jul%22%2C%222021+Aug%22%2C%222021+Sep%22%2C%222021+Oct%22%2C%222021+Nov%22%2C%222021+Dec%22%5D%7D%2C%22type%22%3A%22bar%22%7D",
                 "datasets":[
                    {
                       "pointBorderColor":"rgba(62,149,205, 1)",
                       "backgroundColor":"rgba(62,149,205, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":8625.00,
                          "2021 Aug":55200.00,
                          "2021 Sep":161.00,
                          "2021 Oct":4874.16,
                          "2021 Nov":1323.65,
                          "2021 Dec":2351.75
                       },
                       "borderWidth":"1",
                       "label":"Revenue"
                    },
                    {
                       "pointBorderColor":"rgba(60,186,159, 1)",
                       "backgroundColor":"rgba(60,186,159, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":0,
                          "2021 Aug":0,
                          "2021 Sep":0,
                          "2021 Oct":2268.00,
                          "2021 Nov":864.00,
                          "2021 Dec":1365.55
                       },
                       "borderWidth":"1",
                       "label":"Expenses"
                    },
                    {
                       "pointBorderColor":"rgba(196,88,80, 1)",
                       "backgroundColor":"rgba(196,88,80, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":8625.00,
                          "2021 Aug":55200.00,
                          "2021 Sep":161.00,
                          "2021 Oct":4874.16,
                          "2021 Nov":1323.65,
                          "2021 Dec":2351.75
                       },
                       "borderWidth":"1",
                       "label":"Revenue"
                    },
                    {
                       "pointBorderColor":"rgba(142,94,162, 1)",
                       "backgroundColor":"rgba(142,94,162, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":0,
                          "2021 Aug":0,
                          "2021 Sep":0,
                          "2021 Oct":2268.00,
                          "2021 Nov":864.00,
                          "2021 Dec":1365.55
                       },
                       "borderWidth":"1",
                       "label":"Expenses"
                    }
                 ],
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":null,
                    "dateControls":null,
                    "description":"Revenue VS Expenses",
                    "reportType":"REPARTITION",
                    "numberFormat":null,
                    "money":true,
                    "name":"Revenue VS Expenses",
                    "chartType":"bar",
                    "reportStyle":null,
                    "id":10058,
                    "splitByField":null,
                    "gridWidth":2
                 },
                 "labels":[
                    "2021 Jun",
                    "2021 Jul",
                    "2021 Aug",
                    "2021 Sep",
                    "2021 Oct",
                    "2021 Nov",
                    "2021 Dec"
                 ]
              },
              {
                 "imageUrl":null,
                 "singleValue":251555.55,
                 "singleValueString":"R 251,555.55",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":"amountOutstanding",
                    "stacked":false,
                    "dataClass":"Expense",
                    "dateControls":null,
                    "description":"Outstanding Payments To Suppliers",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":null,
                    "money":true,
                    "name":"Outstanding Payments To Suppliers",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10057,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "imageUrl":"https:\/\/quickchart.io\/chart?c=%7B%22data%22%3A%7B%22datasets%22%3A%5B%7B%22pointBorderColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C8625.00%2C55200.00%2C161.00%2C4874.16%2C1323.65%2C2351.75%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Revenue%22%7D%2C%7B%22pointBorderColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C0%2C0%2C0%2C2268.00%2C864.00%2C1365.55%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Expenses%22%7D%5D%2C%22labels%22%3A%5B%222021+Jun%22%2C%222021+Jul%22%2C%222021+Aug%22%2C%222021+Sep%22%2C%222021+Oct%22%2C%222021+Nov%22%2C%222021+Dec%22%5D%7D%2C%22type%22%3A%22bar%22%7D",
                 "datasets":[
                    {
                       "pointBorderColor":"rgba(62,149,205, 1)",
                       "backgroundColor":"rgba(62,149,205, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":8625.00,
                          "2021 Aug":55200.00,
                          "2021 Sep":161.00,
                          "2021 Oct":4874.16,
                          "2021 Nov":1323.65,
                          "2021 Dec":2351.75
                       },
                       "borderWidth":"1",
                       "label":"Revenue"
                    },
                    {
                       "pointBorderColor":"rgba(60,186,159, 1)",
                       "backgroundColor":"rgba(60,186,159, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":0,
                          "2021 Aug":0,
                          "2021 Sep":0,
                          "2021 Oct":2268.00,
                          "2021 Nov":864.00,
                          "2021 Dec":1365.55
                       },
                       "borderWidth":"1",
                       "label":"Expenses"
                    }
                 ],
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":null,
                    "dateControls":null,
                    "description":"Revenue VS Expenses",
                    "reportType":"REPARTITION",
                    "numberFormat":null,
                    "money":true,
                    "name":"Revenue VS Expenses",
                    "chartType":"bar",
                    "reportStyle":null,
                    "id":10056,
                    "splitByField":null,
                    "gridWidth":2
                 },
                 "labels":[
                    "2021 Jun",
                    "2021 Jul",
                    "2021 Aug",
                    "2021 Sep",
                    "2021 Oct",
                    "2021 Nov",
                    "2021 Dec"
                 ]
              },
              {
                 "imageUrl":null,
                 "singleValue":23,
                 "singleValueString":"23",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":"Customer",
                    "dateControls":null,
                    "description":"Number of Clients",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Clients",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10052,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "dim2Keys":[
                    "All"
                 ],
                 "imageUrl":null,
                 "tabularData":{
                    "Joe Exotica":{
                       "All":6
                    },
                    "James Dean Inc.":{
                       "All":9
                    },
                    "Biznessjet":{
                       "All":2
                    },
                    "Caroline Baskin":{
                       "All":1
                    },
                    "George Sechu":{
                       "All":12
                    },
                    "Steven":{
                       "All":1
                    },
                    "Cti":{
                       "All":1
                    },
                    "JD Enterprises":{
                       "All":1
                    }
                 },
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":"Requisition",
                    "dateControls":null,
                    "description":"Sales Orders per Client",
                    "reportType":"REPARTITION",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Sales Orders per Client",
                    "chartType":"bar",
                    "reportStyle":"TABULAR",
                    "id":10046,
                    "splitByField":"customer",
                    "gridWidth":2
                 },
                 "labels":null
              },
              {
                 "dim2Keys":[
                    "All"
                 ],
                 "imageUrl":null,
                 "tabularData":{
                    "MJ Japa":{
                       "All":19.50
                    },
                    "George Sechu":{
                       "All":17.00
                    },
                    "Steven Sajja":{
                       "All":5.00
                    },
                    "Kim Sono":{
                       "All":7.50
                    }
                 },
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":"hours",
                    "stacked":false,
                    "dataClass":"Hours",
                    "dateControls":null,
                    "description":"Hours worked per employee",
                    "reportType":"REPARTITION",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Hours per Employee",
                    "chartType":"bar",
                    "reportStyle":"TABULAR",
                    "id":10045,
                    "splitByField":"teamMember",
                    "gridWidth":2
                 },
                 "labels":null
              },
              {
                 "dim2Keys":[
                    "All"
                 ],
                 "imageUrl":null,
                 "tabularData":{
                    "Joe Exotica":{
                       "All":4703.50
                    },
                    "James Dean Inc.":{
                       "All":21794.11
                    },
                    "Caroline Baskin":{
                       "All":55200.00
                    },
                    "George Sechu":{
                       "All":190596.40
                    },
                    "Steven":{
                       "All":9361.90
                    },
                    "Viator.com":{
                       "All":66407.84
                    },
                    "GetYourGuide":{
                       "All":3439.01
                    },
                    "Company ABC":{
                       "All":4542.50
                    },
                    "JD Enterprises":{
                       "All":4432.50
                    }
                 },
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":"total",
                    "stacked":false,
                    "dataClass":"Invoice",
                    "dateControls":null,
                    "description":"Revenue per Client",
                    "reportType":"REPARTITION",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Revenue per Client",
                    "chartType":"bar",
                    "reportStyle":"TABULAR",
                    "id":10044,
                    "splitByField":"customer",
                    "gridWidth":2
                 },
                 "labels":null
              },
              {
                 "imageUrl":null,
                 "singleValue":0,
                 "singleValueString":"0",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":"Quotation",
                    "dateControls":null,
                    "description":"Quotations this Month",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Quotations this Month",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10029,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "imageUrl":null,
                 "singleValue":0,
                 "singleValueString":"0",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":"Job",
                    "dateControls":null,
                    "description":"Jobs this Month",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Jobs this Month",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10030,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "imageUrl":"https:\/\/quickchart.io\/chart?c=%7B%22data%22%3A%7B%22datasets%22%3A%5B%7B%22pointBorderColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C8625.00%2C55200.00%2C161.00%2C4874.16%2C1323.65%2C2351.75%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Revenue%22%7D%2C%7B%22pointBorderColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2860%2C186%2C159%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B0%2C0%2C0%2C0%2C2268.00%2C864.00%2C1365.55%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Expenses%22%7D%5D%2C%22labels%22%3A%5B%222021+Jun%22%2C%222021+Jul%22%2C%222021+Aug%22%2C%222021+Sep%22%2C%222021+Oct%22%2C%222021+Nov%22%2C%222021+Dec%22%5D%7D%2C%22type%22%3A%22bar%22%7D",
                 "datasets":[
                    {
                       "pointBorderColor":"rgba(62,149,205, 1)",
                       "backgroundColor":"rgba(62,149,205, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":8625.00,
                          "2021 Aug":55200.00,
                          "2021 Sep":161.00,
                          "2021 Oct":4874.16,
                          "2021 Nov":1323.65,
                          "2021 Dec":2351.75
                       },
                       "borderWidth":"1",
                       "label":"Revenue"
                    },
                    {
                       "pointBorderColor":"rgba(60,186,159, 1)",
                       "backgroundColor":"rgba(60,186,159, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2021 Jun":0,
                          "2021 Jul":0,
                          "2021 Aug":0,
                          "2021 Sep":0,
                          "2021 Oct":2268.00,
                          "2021 Nov":864.00,
                          "2021 Dec":1365.55
                       },
                       "borderWidth":"1",
                       "label":"Expenses"
                    }
                 ],
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":null,
                    "dateControls":null,
                    "description":"Revenue VS Expenses",
                    "reportType":"REPARTITION",
                    "numberFormat":null,
                    "money":true,
                    "name":"Revenue VS Expenses",
                    "chartType":"bar",
                    "reportStyle":null,
                    "id":10033,
                    "splitByField":null,
                    "gridWidth":2
                 },
                 "labels":[
                    "2021 Jun",
                    "2021 Jul",
                    "2021 Aug",
                    "2021 Sep",
                    "2021 Oct",
                    "2021 Nov",
                    "2021 Dec"
                 ]
              },
              {
                 "imageUrl":null,
                 "singleValue":2351.75,
                 "singleValueString":"R 2,351.75",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":"total",
                    "stacked":false,
                    "dataClass":"Invoice",
                    "dateControls":null,
                    "description":"Revenue this Month",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":null,
                    "money":true,
                    "name":"Revenue this Month",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10034,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "imageUrl":null,
                 "singleValue":1365.55,
                 "singleValueString":"R 1,365.55",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":"amount",
                    "stacked":false,
                    "dataClass":"Expense",
                    "dateControls":null,
                    "description":"Expenses this Month",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":null,
                    "money":true,
                    "name":"Expenses this Month",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10035,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "imageUrl":null,
                 "singleValue":16,
                 "singleValueString":"16",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":"TeamMember",
                    "dateControls":null,
                    "description":"Number of Employees",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Employees",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10036,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "imageUrl":null,
                 "singleValue":23,
                 "singleValueString":"23",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":"Customer",
                    "dateControls":null,
                    "description":"Number of Clients",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Clients",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10037,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "locationData":[
                    {
                       "location":{
                          "address":"50 Long St, Cape Town City Centre, Cape Town, 8000, South Africa",
                          "latitude":-33.92152,
                          "longitude":18.41994
                       },
                       "label":"Caroline Baskin",
                       "id":10033,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"",
                          "latitude":-33.659354,
                          "longitude":18.989447
                       },
                       "label":"John Dock",
                       "id":10022,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"",
                          "latitude":-33.659354,
                          "longitude":18.989447
                       },
                       "label":"George Sechu",
                       "id":10021,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"",
                          "latitude":-33.659354,
                          "longitude":18.989447
                       },
                       "label":"Biznessjet",
                       "id":10018,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"",
                          "latitude":-33.659354,
                          "longitude":18.989447
                       },
                       "label":"Kili Inc.",
                       "id":10015,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "country":"",
                          "address":", ,  ",
                          "latitude":-33.659354,
                          "longitude":18.989447
                       },
                       "label":"George Sechu",
                       "id":10014,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"",
                          "latitude":-33.659354,
                          "longitude":18.989447
                       },
                       "label":"Cti",
                       "id":10010,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"6 Grotto Rd, Rondebosch, Cape Town, 7700, South Africa",
                          "latitude":-33.9593305,
                          "longitude":18.4693579
                       },
                       "label":"James Dean Inc.",
                       "id":10009,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"10 Long St, Cape Town City Centre, Cape Town, 8000, South Africa",
                          "latitude":-33.9194856,
                          "longitude":18.4221544
                       },
                       "label":"Company ABC",
                       "id":10004,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"3 Grotto Rd, Rondebosch, Cape Town, 7700, South Africa",
                          "latitude":-33.959752,
                          "longitude":18.469449
                       },
                       "label":"Joe Exotica",
                       "id":10003,
                       "type":"Customer"
                    },
                    {
                       "location":{
                          "address":"6 Grotto Rd, Rondebosch, Cape Town, 7700, South Africa",
                          "latitude":-33.9593305,
                          "longitude":18.4693579
                       },
                       "label":"JD Enterprises",
                       "id":10002,
                       "type":"Customer"
                    }
                 ],
                 "imageUrl":null,
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":"location",
                    "stacked":false,
                    "dataClass":"Customer",
                    "dateControls":null,
                    "description":null,
                    "reportType":"LOCATION",
                    "numberFormat":"R #,###,###,##0.00%",
                    "money":null,
                    "name":"Customers by Location",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10038,
                    "splitByField":null,
                    "gridWidth":2
                 },
                 "labels":null
              },
              {
                 "dim2Keys":[
                    "All"
                 ],
                 "imageUrl":null,
                 "tabularData":{
                    "James Dean Inc.":{
                       "All":5
                    },
                    "Joe Exotica":{
                       "All":2
                    },
                    "Viator.com":{
                       "All":32
                    },
                    "GetYourGuide":{
                       "All":18
                    },
                    "Steven":{
                       "All":3
                    },
                    "JD Enterprises":{
                       "All":4
                    },
                    "Company ABC":{
                       "All":1
                    }
                 },
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":null,
                    "stacked":false,
                    "dataClass":"Job",
                    "dateControls":null,
                    "description":"Jobs per Client",
                    "reportType":"REPARTITION",
                    "numberFormat":"#,###",
                    "money":null,
                    "name":"Jobs per Client",
                    "chartType":"bar",
                    "reportStyle":"TABULAR",
                    "id":10039,
                    "splitByField":"customer",
                    "gridWidth":2
                 },
                 "labels":null
              },
              {
                 "dim2Keys":[
                    "All"
                 ],
                 "imageUrl":null,
                 "tabularData":{
                    "Muffin - Chocolate":{
                       "All":1.00
                    },
                    "Afrocrown Hat!":{
                       "All":4.00
                    },
                    "Dell Laptop":{
                       "All":1.00
                    },
                    "Whole Bird":{
                       "All":2.20
                    },
                    "Nike Shoes":{
                       "All":1.00
                    },
                    "Full Chicken":{
                       "All":3.00
                    },
                    "Regulator - 4KG":{
                       "All":2.60
                    },
                    "Test Prod 1622712576194":{
                       "All":1.00
                    },
                    "Teleshirt":{
                       "All":2.00
                    },
                    "Warm Bread":{
                       "All":7.00
                    },
                    "Papaya":{
                       "All":10.00
                    },
                    "Broccolli":{
                       "All":5.00
                    },
                    "Keyboards":{
                       "All":2.00
                    }
                 },
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":"quantity",
                    "stacked":false,
                    "dataClass":"ProductItem",
                    "dateControls":null,
                    "description":"Products Sold",
                    "reportType":"REPARTITION",
                    "numberFormat":null,
                    "money":null,
                    "name":"Products Sold",
                    "chartType":null,
                    "reportStyle":"TABULAR",
                    "id":10040,
                    "splitByField":"product",
                    "gridWidth":4
                 },
                 "labels":null
              },
              {
                 "imageUrl":"https:\/\/quickchart.io\/chart?c=%7B%22data%22%3A%7B%22datasets%22%3A%5B%7B%22pointBorderColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22backgroundColor%22%3A%22rgba%2862%2C149%2C205%2C+1%29%22%2C%22borderColor%22%3A%22rgba%28255%2C255%2C255%2C1%29%22%2C%22data%22%3A%5B1.00%2C0%2C6.00%2C0%2C0%2C0%2C20.00%2C0%2C0%2C0%2C0%2C0%2C0%2C4.00%2C3.00%2C0%2C0%2C0%2C8.80%2C3.00%2C6.00%5D%2C%22borderWidth%22%3A%221%22%2C%22label%22%3A%22Products+Sold+Over+Time%22%7D%5D%2C%22labels%22%3A%5B%222020+Apr%22%2C%222020+May%22%2C%222020+Jun%22%2C%222020+Jul%22%2C%222020+Aug%22%2C%222020+Sep%22%2C%222020+Oct%22%2C%222020+Nov%22%2C%222020+Dec%22%2C%222021+Jan%22%2C%222021+Feb%22%2C%222021+Mar%22%2C%222021+Apr%22%2C%222021+May%22%2C%222021+Jun%22%2C%222021+Jul%22%2C%222021+Aug%22%2C%222021+Sep%22%2C%222021+Oct%22%2C%222021+Nov%22%2C%222021+Dec%22%5D%7D%2C%22type%22%3A%22bar%22%7D",
                 "datasets":[
                    {
                       "pointBorderColor":"rgba(62,149,205, 1)",
                       "backgroundColor":"rgba(62,149,205, 1)",
                       "borderColor":"rgba(255,255,255,1)",
                       "data":{
                          "2020 Apr":1.00,
                          "2020 May":0,
                          "2020 Jun":6.00,
                          "2020 Jul":0,
                          "2020 Aug":0,
                          "2020 Sep":0,
                          "2020 Oct":20.00,
                          "2020 Nov":0,
                          "2020 Dec":0,
                          "2021 Jan":0,
                          "2021 Feb":0,
                          "2021 Mar":0,
                          "2021 Apr":0,
                          "2021 May":4.00,
                          "2021 Jun":3.00,
                          "2021 Jul":0,
                          "2021 Aug":0,
                          "2021 Sep":0,
                          "2021 Oct":8.80,
                          "2021 Nov":3.00,
                          "2021 Dec":6.00
                       },
                       "borderWidth":"1",
                       "label":"Products Sold Over Time"
                    }
                 ],
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":4,
                    "dataField":"quantity",
                    "stacked":true,
                    "dataClass":"ProductItem",
                    "dateControls":null,
                    "description":"Products Sold Over Time",
                    "reportType":"TIME_BASED",
                    "numberFormat":null,
                    "money":null,
                    "name":"Products Sold Over Time",
                    "chartType":"bar",
                    "reportStyle":null,
                    "id":10041,
                    "splitByField":null,
                    "gridWidth":2
                 },
                 "labels":[
                    "2020 Apr",
                    "2020 May",
                    "2020 Jun",
                    "2020 Jul",
                    "2020 Aug",
                    "2020 Sep",
                    "2020 Oct",
                    "2020 Nov",
                    "2020 Dec",
                    "2021 Jan",
                    "2021 Feb",
                    "2021 Mar",
                    "2021 Apr",
                    "2021 May",
                    "2021 Jun",
                    "2021 Jul",
                    "2021 Aug",
                    "2021 Sep",
                    "2021 Oct",
                    "2021 Nov",
                    "2021 Dec"
                 ]
              },
              {
                 "imageUrl":null,
                 "singleValue":84836.94,
                 "singleValueString":"R 84,836.94",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":"amountOutstanding",
                    "stacked":false,
                    "dataClass":"Invoice",
                    "dateControls":null,
                    "description":"Outstanding Payments From Customers",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":null,
                    "money":true,
                    "name":"Outstanding Payments From Customers",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10042,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              },
              {
                 "imageUrl":null,
                 "singleValue":251555.55,
                 "singleValueString":"R 251,555.55",
                 "datasets":null,
                 "params":{
                    
                 },
                 "config":{
                    "gridHeight":2,
                    "dataField":"amountOutstanding",
                    "stacked":false,
                    "dataClass":"Expense",
                    "dateControls":null,
                    "description":"Outstanding Payments To Suppliers",
                    "reportType":"SINGLE_VALUE",
                    "numberFormat":null,
                    "money":true,
                    "name":"Outstanding Payments To Suppliers",
                    "chartType":null,
                    "reportStyle":null,
                    "id":10043,
                    "splitByField":null,
                    "gridWidth":1
                 },
                 "labels":null
              }
           ],
           "status":"ok"
        }
    """.trimIndent()
}