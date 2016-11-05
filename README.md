# Audiogram
[![](https://jitpack.io/v/alxrm/Audiogram.svg)](https://jitpack.io/#alxrm/Audiogram)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()

Super lightweight component to create audiowaves written in Kotlin



<img src="imgs/wave.gif"/>


##Getting started

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
    compile 'com.github.alxrm:Audiogram:0.5'
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

## In code

Settle this wave somewhere in your XML like this:

```xml
	<rm.com.audiowave.AudioWaveView
		android:id="@+id/wave"
		android:layout_width="match_parent"
		android:layout_height="32dp"
		android:layout_margin="16dp"
		app:chunkWidth="3dp"
		app:chunkHeight="24dp"
		app:minChunkHeight="2dp"
		app:chunkSpacing="1dp"
		app:chunkRadius="1dp"
		app:waveColor="@android:color/white"
		/>
```

####There are 2 ways you can work with this component:

* Set raw byte array asynchronously 

```java
    
    // does downsampling in O(N) and shows the animation you see in a gif above (the inflation-like one)
    setRawData(byte[] data);
    
    // you also have the ability to listen, when does the downsampling complete
    setRawData(byte[] data, Function0<Unit> callback);
```

* In case you have scaled byte array you want to draw

```java
    
    // instantly redraws the wave without async downsampling process
    setScaledData(byte[] scaledData);
```

## Contribution

There are some features(like seeking) I am about to implement, but a little bit later. 
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
