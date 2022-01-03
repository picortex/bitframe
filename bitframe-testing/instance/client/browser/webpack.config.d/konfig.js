config.resolve.modules.push("/media/andylamax/workspace/PiCortex/bitframe/bitframe-testing/instance/client/browser/build/resources/js")
config.module.rules.push({
    test: /\.(png|jpe?g|gif|svg)$/i,
    use: [
      {
        loader: 'file-loader',
      },
    ],
});
config.devServer = { ...config.devServer, historyApiFallback: true, host: "0.0.0.0" }