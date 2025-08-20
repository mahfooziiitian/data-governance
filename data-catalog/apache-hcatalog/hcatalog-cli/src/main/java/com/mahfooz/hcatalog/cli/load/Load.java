/*
To load data in HCatalog, LOAD statement is used. SYNTAX:

LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename
[PARTITION (partcol1=val1, partcol2=val2 ...)]
Example:

LOCAL is the identifier to specify the local path. It is optional.
OVERWRITE is optional to overwrite the data in the table.
PARTITION is optional


 */
package com.mahfooz.hcatalog.cli.load;

public class Load {
}
