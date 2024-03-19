## CLUSTEREDDATA WAREHOUSE
---
:scroll: **START**
## Introduction
Been part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB.
Data warehouse is a central repository of integrated data from one or more disparate sources. It is used for reporting and data analysis. Data warehouse is a subject-oriented, integrated, nonvolatile and time-variant collection of data in support of management's decision making process. Data warehouse is a copy of transaction data specifically structured for querying and analysis. It is a subject-oriented, integrated, nonvolatile and time-variant collection of data in support of management's decision making process. Data warehouse is a copy of transaction data specifically structured for querying and analysis.

---
### Task description
**Request Fields** should contain::
- Deal Unique Id;
- From Currency ISO Code "Ordering Currency";
- To Currency ISO Code;
- Deal timestamp;
- Deal Amount in ordering currency;

---
### Requirements

- System should not import same request twice.
- Validate row structure.(e.g: Missing fields, Type format..etc.
- No rollback allowed, what every rows imported should be saved in DB

---
#### How to build
#### Requirements
- Java 17;
- Java IDE : IntelliJ (or Eclipse, Vscode, Netbeans);
- Database: Postgres;
- Postman(For testing);
  Steps by step for building and running the project locally;
  Clone from the link git clone https://github.com/Chesca22/ClusteredDatawareHouse
- Open the cloned project in intelliJ Idea;
- Go to POM.xml the update Project to update all the maven dependencies;
- Maven Build the project and run;
---
#### Testing The API Endpoints
---
**Accepting Deal details**
- Endpoint: 'http://localhost:8089/api/deals';
```
curl --location --request POST 'http://localhost:8089/api/deals' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
                    "dealId": "1577A",
                    "orderingCurrency": "usd",
                    "convertedCurrency": "ngn",
                    "dealAmount": "456.00"
             }'
````
**Response**
```
'{
                 "body": "FXDeal details with Id number157Asaved successfully, Time: 2024-03-19T16:51:09.008400500",
                 "statusCode": "CREATED",
                "statusCodeValue": 201
            }'
```
---

**Getting all deals details**
- Endpoint: '(http://localhost:8089/api/v1/deals)';
  **Payload**
```
curl --location --request GET '(http://localhost:8089/api/v1/deals)'
````
**Response**
```
{
    "message": "status",
    "data": [
        {
        "dealId": "39406",
        "orderingCurrency": "USD",
        "convertedCurrency": "NGN",
        "amount": "5987.00",
        "dealTimeStamp": "2024-03-19T13:19:59.722176"
    },
    {
        "dealId": "46894",
        "orderingCurrency": "PLN",
        "convertedCurrency": "EUR",
        "amount": "1500.00",
        "dealTimeStamp": "2024-03-19T13:31:05.477708"
    }
    ]
}
```

