#sqoop import  --connect <jdbc-url> -table <table-name> --hcatalog-table txn <other sqoop options>

sqoop export \
  --connect <jdbc-url> \
  --table <table-name> \
  --hcatalog-table txn <other sqoop options>

