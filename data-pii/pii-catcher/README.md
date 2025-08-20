# Setup
    
    pip install piicatcher
    pip install piicatcher_spacy

# SQLITE

    piicatcher catalog add-sqlite --name sqldb --path "C:\Users\Mohammed_Alam\learning\data\Database\Setup\sqllite"
    piicatcher detect --source-name sqldb
    
    piicatcher catalog add-postgresql --name pg_db --path "C:\Users\Mohammed_Alam\learning\data\PII\postgres"

