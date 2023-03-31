# turning up zookeeper
docker compose -f common.yml -f zookeeper.yml up

# and kafka cluster
docker compose -f common.yml -f kafka_cluster.yml up

# one time init kafka
docker compose -f common.yml -f init_kafka.yml up
# for troubleshooting one can delete volumes folder inside docker-compose

#installing kcat is important
kcat -C -b localhost:19092 -t restaurant-approval-request
kcat -C -b localhost:19092 -t payment-request

# postgres container spinning commands with required user, db and password

docker run -d \
	--name postgres \
    -p 5432:5432 \
	-e POSTGRES_DB=postgres \
	-e POSTGRES_USER=postgres \
	-e POSTGRES_PASSWORD=password \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-v /home/bharat/data/postgres:/var/lib/postgresql/data \
	postgres

# check if that is working fine or not

psql -h localhost -U postgres

# password is password

# Link to work on PSQL - https://chartio.com/resources/tutorials/how-to-list-databases-and-tables-in-postgresql-using-psql/

