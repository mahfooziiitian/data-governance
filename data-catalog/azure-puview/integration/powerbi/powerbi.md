# PowerBI integration

## Register and scan a Power BI tenant

To get an understanding of what is going on in your Power BI tenant, you can perform a full scan in Microsoft Purview to view the schema and lineage of assets across all workspaces. After, you can schedule incremental scans on workspaces that have changed since the previous scan.

There are a few pre-requisite steps required to scan your Power BI tenant in Microsoft Purview.

### 1. Establish a connection between Microsoft Purview and Power BI

Microsoft Purview can connect to and scan Power BI either in the same tenant or across tenants. You'll need to set up authentication either by using a Managed Identity or a Delegated Authentication.

### 2. Authenticate to Power BI tenant

Give Microsoft Purview permissions to access your Power BI tenant.

If you're using Managed Identity to authenticate to Power BI, you'll need to create a security group in Azure Active Directory, and add your Microsoft Purview managed identity to this security group.

If a security group containing the Purview managed identity already exists, you can proceed to configuring the Power BI tenant.

### 3. Configure Power BI tenant

Next you need to enable access to Power BI by Microsoft Purview in Power BI itself. This is done by enabling Allow service principals to use read-only Power BI admin APIs in the Power BI admin portal.

### 4. Search and browse Power BI assets

After data is registered and scanned, analysts and data consumers need to be able to find data, view enhanced metadata, and track data lineage. Search and browse in the Purview Data Catalog enables you to quickly find trustworthy data.

After scanning your Power BI tenant, you'll see those assets appear in the search results, including underlying data sources.

### 5. Search the Microsoft Purview Data Catalog

From the Microsoft Purview Governance Portal, you can type relevant keywords to start discovering assets. In this scenario, you're looking for "sales."

Browse the Microsoft Purview Data Catalog

### 6. View Power BI metadata and lineage
