mkdir /picortex/apps/pimonitor/${{ steps.versions.outputs.current }}/staging -p
mv /picortex/apps/pimonitor/${{ steps.versions.outputs.current }}/staging/pimonitor/pimonitor-app/server/build/docker/docker-compose-staging.yml /picortex/apps/pimonitor/${{ steps.versions.outputs.current }}/staging/docker-compose.yml
cd /picortex/apps/pimonitor/${{ steps.versions.outputs.current }}/staging
rm pimonitor -rf
docker-compose pull
docker stack deploy -c docker-compose.yml pimonitor-staging-${{ steps.versions.outputs.current_safe }}