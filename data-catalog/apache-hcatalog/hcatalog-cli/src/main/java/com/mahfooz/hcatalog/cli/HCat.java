/*

HCatalog Command Line Interface (CLI) can be invoked from the command $HIVE_HOME/HCatalog/bin/hcat where $HIVE_HOME is the home directory of Hive.
hcat is a command used to initialize the HCatalog server.

Creating table
    hcat -e "create table groups(name string,placeholder string,id int) \
    row format delimited fields terminated by ':' stored as textfile"

    hcat -e "create table groupids(name string,id int)"


Describe the table
    hcat -e "desc groups"


 */
package com.mahfooz.hcatalog.cli;

/**
 *
 * @author 370037
 */
public class HCat {
    
}
