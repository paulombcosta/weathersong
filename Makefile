up:
	docker-compose up -d
stop:
	docker-compose stop
destroy: stop
	docker-compose rm -f
