# Audiogram
[![](https://jitpack.io/v/alxrm/audiowave-progressbar.svg)](https://jitpack.io/#alxrm/audiowave-progressbar)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()

Super lightweight audiowave progressbar written in Kotlin



<img src="imgs/wave.gif"/>


## Getting started

Add to your root build.gradle:
```Groovy
allprojects {
	repositories {
	    ...
	    maven { url "https://jitpack.io" }
	}
}
```

Add the dependency:
```Groovy
dependencies {
  implementation 'com.github.alxrm:audiowave-progressbar:0.9.2'
}
```

## Attrs
|attr|format|description|
|---|:---|:---:|
|waveColor|color|with this color chunks will be filled|
|chunkWidth|dimension|every chunk will have this width|
|chunkHeight|dimension|maximum height all the chunks can be|
|minChunkHeight|dimension|minimum height all the chunks can be|
|chunkSpacing|dimension|spacing between chunks|
|chunkRadius|dimension|how much corners of every chunk will be rounded|
|progress|float|should be 0..100, it's float so you can easily animate this|
|animateExpansion|boolean|toggle the animated expansion|
|touchable|boolean|toggle to use it as a seekbar, default value is `true`|

__Note: If you are going to place this in a RecyclerView item, you have to set `animateExpansion` to `false`, otherwise you'll see an incredibly laggy scroll (check out the example)__

## In code

Settle the wave somewhere in your XML like this:

```xml
<rm.com.audiowave.AudioWaveView
    android:id="@+id/wave"
    android:layout_width="match_parent"
    android:layout_height="32dp"
    android:layout_margin="16dp"
    app:animateExpansion="false"
    app:chunkWidth="3dp"
    app:chunkHeight="24dp"
    app:minChunkHeight="2dp"
    app:chunkSpacing="1dp"
    app:chunkRadius="1dp"
    app:touchable="true"
    app:waveColor="@android:color/white"
    />
```

## API

Set raw byte array asynchronously 

```java
// does downsampling in O(N) and shows the animation you see in a gif above (the inflation-like one)
setRawData(byte[] data);

// you also have the ability to listen, when does the downsampling complete
setRawData(byte[] data, OnSamplingListener callback);
```

In case you have a scaled byte array you want to draw, i. e. an array whose size is the amount of chunks to draw


```java
// instantly redraws the wave without async downsampling process
setScaledData(byte[] scaledData);
```

You can use it like a `Seekbar`, it reacts on touches, just attach listener

```java
wave.setOnProgressListener(OnProgressListener listener);
```

__Note:__ that `setOnProgressListener` is a Java style API and with Kotlin you have to set it like this:

```kotlin
wave.onProgressListener = object : OnProgressListener {...}
```

This listener has 3 methods like a built-in `Seekbar`

```java
void onStartTracking(float progress) {
  // invokes when user touches the view
}

void onStopTracking(float progress) {
  // invokes when user releases the touch
}

void onProgressChanged(float progress, boolean byUser) {
  // invokes every time the progress's been changed
}
```

__Kotlin__ users can listen `Seekbar` event in a more convenient and clean way with __properties__:

```kotlin
wave.onStopTracking = {
  Log.d("wave", "Progress set: $it")
}

wave.onStartTracking = {
  Log.d("wave", "Started tracking from: $it")
}

wave.onProgressChanged = {progress, byUser ->
  Log.d("wave", "Progress set: $progress, and it's $byUser that user did this")
}
```

## Contribution

There are some features(like better precision) I am about to implement, but a little bit later. 
If you'd like to help, you are always free to send pull requests or issues if you only want to suggest something 

**Note:** All your pull requests should be written in kotlin

## License

    MIT License

    Copyright (c) 2016 Alexey Derbyshev

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
