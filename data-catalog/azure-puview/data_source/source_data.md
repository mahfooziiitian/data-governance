# Sourcing your data

It starts with a process where you register data sources. Microsoft Purview supports an array of data sources that span on-premises, multicloud, and software-as-a-service (SaaS) options. You register the various data sources so that Microsoft Purview is aware of them. The data remains in its location and isn't migrated to any other platform.

## authentication for data source

there are a few options to use for authentication when the service needs to scan data sources. Some of these options are:

1. Microsoft Purview managed identity
2. Account key (using Azure Key Vault)
3. SQL authentication (using Key Vault)
4. Service principal (using Key Vault)
