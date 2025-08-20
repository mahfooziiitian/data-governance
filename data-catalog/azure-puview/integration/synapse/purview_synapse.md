# Purview synapse

Microsoft Purview can help in this scenario by cataloging the data assets in a data map, and enabling data stewards to add metadata, categorization, subject matter contact details, and other information that helps data analysts identify and understand data.

## Configure data access for Microsoft Purview

In order to scan the data assets in the data lake storage and databases used in your Azure Synapse Workspace, Microsoft Purview must have appropriate permissions to read the data.

In practice, this means that the account used by your Microsoft Purview account (usually a system-assigned managed identity that is created when Microsoft Purview is provisioned) needs to be a member of the appropriate role-based access control (RBAC) and database roles.

The diagram shows that Microsoft Purview requires role membership that permits the following access:

Read access to the Azure Synapse workspace (achieved through membership of the Reader role for the Azure Synapse Workspace resource in the Azure subscription).
Read access to each SQL database that will be scanned (achieved through membership of the db_datareader fixed database role in each database).
Read access to data lake storage (achieved through membership of the Storage Blob Data Reader role for the Azure Storage account hosting the Azure Data Lake Storage Gen2 container for the data lake).

## Register and scan data sources

Microsoft Purview supports the creation of a data map that catalogs data assets in collections by scanning registered sources. Collections form a hierarchy of logical groupings of related data assets, under a root collection that is created when you provision a Microsoft Purview account. You can use the Microsoft Purview Governance Portal to create and manage collections in your account.

To include assets from a particular data source, you need to register the source in a collection. Microsoft Purview supports many kinds of source, including:

Azure Synapse Analytics - One or more SQL databases in a Synapse Analytics workspace.
Azure Data Lake Storage Gen2 - Blob containers used to host folders and files in a data lake.

## View and manage cataloged data assets

As each scan finds data assets in the registered sources, they're added to the associated collection in the data catalog.

## Connect Microsoft Purview to an Azure Synapse Analytics workspace

By linking your workspace to a Purview account, you can:

Search the Purview catalog in the Synapse Studio user interface.
Push details of data pipeline activities to Purview in order to track data lineage information.

### Security considerations

To connect a Purview account by using the Synapse Studio interface, you require Collection Administrator access to the Purview account's root collection. After successfully connecting the account, the managed identity used by your Azure Synapse Analytics workspace will be added to the collection's Data Curator role.

## Track data lineage in pipelines

### Generate and view data lineage information
