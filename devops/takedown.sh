root=$1
echo "Taking down /$root"
docker stack rm $root-devops
rm /$root/devops -rf
docker swarm leave --force
echo "Done"