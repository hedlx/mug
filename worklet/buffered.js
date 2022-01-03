class BufferProcessor extends AudioWorkletProcessor {
  static get parameterDescriptors() {
    return [{
      name: 'stopped',
      defaultValue: false
    }];
  }

  constructor (...args) {
    super(...args)
    this.buffer = []
    this.size = 0
    this.notified = false

    this.port.onmessage = (e) => {
      this.size = e.data.length
      this.buffer = this.buffer.concat(e.data)
      this.notified = false
    }
  }

  process (_inputs, outputs, params) {
    if (params.stopped.slice(-1)[0]) {
      return true
    }

    const len = outputs[0][0].length
    const values = this.buffer.slice(0, len)
    this.buffer = this.buffer.slice(len)

    if (values.length < len) {
      return true
    }

    if (!this.notified && (this.buffer.length < (this.size / 2))) {
      this.notified = true
      this.port.postMessage('DATA_REQUIRED')
    }

    outputs.forEach(
      output => output
        .forEach(samples => {
          for (let i = 0; i < samples.length; i++) {
            samples[i] = values[i]
          }
        }))

    return true
  }
}

registerProcessor('buffer-processor', BufferProcessor)