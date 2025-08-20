# Data Vault

A data vault is a data modeling design pattern used to build a data warehouse for enterprise-scale analytics.

Data Vault modeling is a data modeling methodology designed to provide long-term historical storage of data coming from multiple sources.

It is particularly well-suited for data warehousing and business intelligence solutions, offering flexibility, scalability, and maintainability.

The data vault has three types of entities:

1. hubs
2. links
3. satellites

## Hub

Hubs represent core business concepts, links represent relationships between hubs, and satellites store information about hubs and relationships between them.

Hubs - Each hub represents a core business concept, such as they represent Customer Id/Product Number/Vehicle identification number (VIN). Users will use a business key to get information about a Hub. The business key may have a combination of business concept ID and sequence ID, load date, and other metadata information.

## Links

Links represent the relationship between Hub entities.

## Satellites

Satellites fill the gap in answering the missing descriptive information on core business concepts. Satellites store information that belongs to Hub and relationships between them.
A few additional things to keep in mind:

A satellite cannot have a direct connection to another satellite.
A hub or link may have one or more satellites.

## Data vault benefits

1. Agile
2. Structured, with flexibility for refactoring
3. Extremely scalable, up to PBs volumes
4. Uses patterns that support ETL code generation
5. Familiar architecture: data layers, ETL, star schemas
6. Data vaults are based on agile methodologies and techniques, which means that they can adapt to fast-paced changing business requirements. One

One of the major advantages of using the Data Vault methodology is that ETL jobs need less refactoring when the model changes.

## Modeling techniques by lake-house layer

With these concepts in mind, let's explore how Data Vault fits into our `Bronze, Silver and Gold data` layers where data goes from a raw to a refined state that is ready for analytics.

In this multi-hop architecture, raw data gets stored in a Bronze layer with minimum transformation and data structure as close to the source system.

The Data Vault methodology can be applied to the Silver layer where data is transformed into Hubs, links and satellites.

In the Gold layer, multiple data marts/data warehouses can be built as per `dimensional modeling/Kimball methodology`.

As discussed earlier, the `Gold layer` is for reporting and uses more de-normalized and read-optimized data models with fewer joins.

Sometimes tables in the `Gold Layer` can be completely denormalized, typically if the Data Scientists want it that way to feed their algorithms for feature engineering.

If a Data Vault model is used in the `Silver layer` it simplifies and significantly reduces the changes needed to perform ETL into the Data Marts and Data Warehouses, as Hubs make key management (surrogate key / natural keys) easier.

Satellites make loading dimensions easier because they have all the attributes, and links make loading fact tables quite straightforward because they have all the relationships.
