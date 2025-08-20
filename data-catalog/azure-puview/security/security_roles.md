# Roles

New users must be put in one or more of the following roles:

1. **Purview Data Reader role:** Has access to the Microsoft Purview governance portal and can read all content in Microsoft Purview except for scan bindings.
2. **Purview Data Curator role:** Has access to the Microsoft Purview governance portal and can read all content in Microsoft Purview except for scan bindings. Can edit information about assets, classification definitions, and glossary terms. Can also apply classifications and glossary terms to assets.
3. **Purview Data Source Administrator role:** Doesn't have access to the Microsoft Purview governance portal because the user must also be in the Data Reader or Data Curator roles. Can manage all aspects of scanning data into Microsoft Purview. Doesn't have read or write access to content in Microsoft Purview beyond those tasks related to scanning.

