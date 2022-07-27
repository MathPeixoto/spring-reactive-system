# spring-reactive-system

In order to expose those services, run: `kubectl expose deployment deployment-name --type=NodePort --name=example-service`
Example: 

`kubectl expose deployment inventory-deployment --type=NodePort --name=inventory-service`




How to curl a service: `sudo curl $(minikube service service-name --url)/path`
Example:

`sudo curl $(minikube service inventory-service --url)/api/products`
