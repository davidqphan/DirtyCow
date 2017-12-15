# DirtyCow CVE-2016-5195 Proof of Concept App

## Authors
David Phan

Joel Gomez - https://github.com/jgome043

## Overview
Dirty COW is a privilege escalation vulnerability that allows an attacker to exploit
a race condition vulnerability in the Copy-On-Write mechanism of the memory management
in the Linux Kernel. The exploit allows a non-privileged user to write to read-only
memory space.

## Technical Issues
1. Using the latest versions of NDK caused the compiled run-as binary to be
significantly larger in size than the [Destination] binary on one of our target
devices (Nexus 4). The size difference was so great that it caused I/O errors
that prevented the exploit from succeeding. Using the oldest version of the NDK
available from the Android Developers website, we managed to get a compiled
version of our desired [Source] and get the exploit to work consistently.
2. In order to use our exploit to get access to a root shell, we use the Dirty COW
exploit to overwrite a read-only binary in the device’s /system/bin path. This
specific binary has the setuid bit that allows the process to run as the root user.
On our Nexus 4 device, run-as has the setuid bit and we’re able to achieve
a root shell when we succeed in overwriting the binary with another program
we compile that uses the system() syscall to spawn a shell. We’ve provided
a video of our demonstration of this version of the attack on YouTube here:
https://www.youtube.com/watch?v=pa8BNty0JGk
3. Unfortunately, we were not this lucky on our Samsung Galaxy Note 4 device.
While the run-as binary was present, it did not have the setuid bit, which would
mean that we would not be able to use the same method to obtain a root shell.
To demonstrate the use of our exploit on this device, we created an Android
application that displays a read-only image and it’s file permissions. We then
use the Dirty COW exploit to overwrite the image and refresh the image on the
device to show that it is a different image after running the exploit.

The proof of concept binary (https://github.com/Tlgyt/DirtyCowAndroid/tree/master/DirtyCow)
was used to demonstrate the use of the exploit.

```
adb devices
adb shell '/path/to/dirtycow_exploit /path/to/read-only-file /path/to/new-file'
```

Here we have the original read-only file on the left with an image of a dirty cow, and the replacement file with
an image of Groot.

<img src="https://user-images.githubusercontent.com/15112219/34063472-2d30b5a2-e1a7-11e7-8fca-c9bee87ef30a.png" width="320" height="480"> <img src="https://user-images.githubusercontent.com/15112219/34063473-30d2c98e-e1a7-11e7-93ee-efd54f4f3000.png" width="320" height="480">
