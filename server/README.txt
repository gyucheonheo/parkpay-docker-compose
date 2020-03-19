Illinois Institute of Technology 

CS445 Final Project Park Pay System

Gyucheon Heo
A20393055

Configuration Instruction

0. Install java
$ sudo apt-get default-jdk

1. Install maven
$ sudo apt-get install maven

Build and Deploy Instruction

2. Assuming that you are in the directory where the pom.xml file resides.
$ mvn clean install

3. Run Test Cases
$ mvn clean test

4. Run the server
$ mvn exec:java


Copyright (c) 2018 Gyucheon Heo at Illinois Institute of Technology

Permission is hereby granted, free of change, to any person obtaining
a copy of this software and associated documentation files (the "Software"),
to deal in Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute and/or sublicense copies of the Software,
and to permit persons to whom the Software is furnished to do so, subject to the following conditions.

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTIO WITH THE SOFTWARE
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Known Bugs

* GET /parkpay/notes response is not identical to the expected result
* Nested Json problem occurs.


Credits and Acknowledgements

Author of the ParkPay Documentation
* Gyucheon Heo

Author of the ParkPay version 1.0
* Gyucheon Heo

Contact Information
* Gyucheon Heo
* email : gheo1[at]hawk[dot]iit[dot]edu
* mobile: +1 312 678 2918
