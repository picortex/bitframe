config.resolve.modules.push("/media/andylamax/workspace/PiCortex/bitframe/pi-monitor/pi-monitor-client/pi-monitor-client-browser/pi-monitor-client-browser-react/build/resources/js")
config.module.rules.push({
    test: /\.(png|jpe?g|gif|svg)$/i,
    use: [
      {
        loader: 'file-loader',
      },
    ],
});
config.devServer = { ...config.devServer, historyApiFallback: true, host: "0.0.0.0" }