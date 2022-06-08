root=$1
wd=$(pwd)
echo "Setting up /$root dir"
mkdir /$root

echo "Setting up /$root docker-registry"
cd /$root
mkdir devops
cd devops
mkdir docker-registry
cd docker-registry
mkdir auth certs data

echo "Setting up /$root npm-registry"
cd /$root/devops
mkdir npm-registry
cd npm-registry
mkdir conf plugins storage
chcon -Rt container_file_t ./conf

echo "Setting up /$root maven-repository"
cd /$root/devops
mkdir maven-repository

echo "Copying configs"
cp $wd/docker-compose.yml /$root/devops/docker-compose.yml
cp $wd/npm-registry/conf/config.yaml /$root/devops/npm-registry/conf/config.yaml
cd /$root/devops
chmod 777 -R .

echo "Preparing docker scripts"
export DEVOPS_ROOT=/$root

echo "Running docker-compose pull"
docker-compose pull

docker swarm init

docker stack deploy -c docker-compose.yml $root-devops

echo "Finished"