## Abstract
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This manual introduces the core functions of the FCGS tool. FCGS provides a Java implementation of the FCGS comparison algorithm, which supports the graphical user interface (GUI) mode and command line calling mode for use of this algorithm. The user can integrate the FCGS algorithm function in their project by selecting the FCGS algorithm function interface in the reference program. The main purpose of this project is to provide the user with an example of the implementation of the FCGS algorithm so that they can better understand the processes and concepts involved with this algorithm.
      
## Project Download
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Project source code address: https://github.com/plantdna/fcgs

### Project Structure
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The project directory contains the source code directory fcgs and the directory data for the test data files. The source code directory of the project contains five submodules (FcgsBase, FcgsCore, FcgsService, IPidsCore, and PidsCore) and a main program module fcgs. The five submodules provide some basic interfaces and implementation for the algorithm, while the main program module provides the functional implementation of performing the comparison algorithm through the graphical interface and command line modes. In the “Data” directory, we provide six files, including microsatellite, SNP KASP, and SNP-array marker types of data, each of which provides “Source” and “Target” fingerprint data in CSV file format. The user can use these files for functional testing. The basic directory structure of the fcgs project is as follows:

```
plantdna/fcgs
 ├──fcgs -- FCGS main project
 |	 ├──fcgs -- FCGS main project
 |	 ├──FcgsBase -- Basic code
 |	 ├──FcgsCore -- Comparison core code
 |	 ├──FcgsService -- Comparison service
 |	 ├──IPidsCore -- Fingerprint comparison interface
 |	 └──PidsCore -- Implementation of fingerprint comparison interface
 ├──data -- Test data file directory
 └──README.md
 ```
 
### Operating Environment
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The FCGS algorithm program runs in advanced Java Development Kit (JDK) version 1.7 (Oracle Corporation, Redwood Shores, CA, USA) or above. Supported operating systemz include: Windows, Linux, and MacOS. Before executing the FCGS algorithm, the user must install the correct version of the JDK package according to their operating system type. The download address is:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://www.oracle.com/technetwork/java/javase/overview/index.html

## Program Packaging
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This program uses Java language for development and the project uses Maven for developing and packaging. The code can be edited and run using IntelliJ IDEA or development tools such as Eclipse. Therefore, it is necessary to pre-configure the parameters of the Maven plug-in for the development tool so it can use Maven to automatically download the required JAR packages and automatically compile and package the source code of the fcgs project.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The fcgs project can be packaged with development tools to export two JAR package files from the main module program. These two files provide the graphical interface mode and command line mode, respectively. It is recommended that the user end the exported JAR file name with GUI or Command (CMD) to distinguish between the two functional JAR files. The jar file description is as follows.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1) The package ending with GUI is named fcgs-gui.jar. This package runs in an operating system with a graphical interface that allows the user to select data files, set parameters, and compare them graphically. When building the fcgs-gui.jar package, the user must select the main program class file as “FcgsGuiRunner.java.”<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2) The package ending with CMD is named fcgs-cmd.jar. This package is mainly used for the invocation of the comparison algorithm in the command line mode. This can be compared using the given parameters and can be easily integrated into different systems to provide comparison services. When building the fcgs-gui. jar package, the user must select the main program class file as “FcgsCmdRunner.java.”<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Description: Development tools such as IntelliJ IDEA and Eclipse generally use the latest version.

## GUI Mode
### Startup Command
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;java –jar fcgs-gui.jar<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note: If the Java environment variable is set, the user can also double-click the Fcgs-gui. jar package to execute it directly. If the file is set incorrectly, the user can use the command above to open it.

#### Operation Workflow
#### Main Interface
![image](https://github.com/plantdna/fcgs/blob/master/data/Main%20Interface.png)

Three types of fingerprint comparison data are supported, as shown in the figure above.<br>1. Microsatellite fingerprint data comparison corresponding to the “Microsatellite” page in the figure.<br>2. SNP KASP fingerprint data comparison corresponding to the “SNP KASP” page in the figure.<br>3. SNP-array fingerprint data comparison corresponding to the “SNP-arrays” page in the figure.

The fingerprint comparison parameters in the figure above are as follows:
<table border="1px">
    <tr style="background-color: gray">
        <td>Parameters</td><td>Parameter Description</td>
    </tr>
    <tr>
        <td width="300">Minimum number of difference loci</td><td rowspan="2">Used to filter difference loci in results so that only results within a given range are displayed. <br>Parameter values may not be given.</td>
    </tr>
    <tr>
        <td>Maximum number of difference loci</td>
    </tr>
    <tr>
        <td>* Source fingerprints CSV</td>
        <td>To compare the path of the fingerprint CSV file. Input required.</td>
    </tr>
    <tr>
        <td>* Comparison fingerprint CSV</td>
        <td>Compare the path of the fingerprint CSV file. Input required.</td>
    </tr>
    <tr>
        <td>Fingerprint CSV template</td>
        <td>Download the fingerprint data template file. Different molecular marker types have different data formats.</td>
    </tr>
    <tr>
        <td>Start Compare</td>
        <td>Start fingerprint comparison</td>
    </tr>
    <tr>
        <td colspan="2"><b>Parameters with * are required input</b></td>
    </tr>
</table>

Instructions for downloading the fingerprint template file:
Click on the “Download Template” button to download the template to the current tab page belonging to the corresponding molecular marker type format file. For example, the content of the downloaded file template in the figure above is “Microsatellite.” The user must switch to the corresponding tab page to download the corresponding data template. If the data format is inconsistent with the content of the template, then the comparison operation will fail.

#### Comparison Results Interface
![image](https://github.com/plantdna/fcgs/blob/master/data/Comparison%20Results%20Interface.png)

The parameters of fingerprint comparison results in the figure above are as follows.

<table border="1">
    <tr style="background-color: gray">
        <td width="350"><b>Field</b></td><td><b>Field Description</b></td>
    </tr>
    <tr>
        <td>Data format</td><td>Molecular marker data format, including: Microsatellite, SNP-KASP, SNP-arrays</td>
    </tr>
    <tr>
        <td>Source fingerprint number</td><td>The number of fingerprints to be compared in CSV file</td>
    </tr>
    <tr>
        <td>Comparison fingerprint number</td><td>The number of fingerprints to be compared in CSV file</td>
    </tr>
    <tr>
        <td>Average number of difference loci</td><td>The average value obtained by dividing the sum of the difference loci in all the comparison results by the number of comparison results which can reflect the information size of the difference loci in the comparison results</td>
    </tr>
    <tr>
        <td>Compare result count</td><td>The number of comparison results is obtained after comparing two fingerprint lists</td>
    </tr>
    <tr>
        <td>Total comparison time(ms)</td><td>Time to call FCGS algorithm for comparison (ms)</td>
    </tr>
    <tr>
        <td>Average comparison time(ms)</td><td>Average time spent per comparison result (ms)</td>
    </tr>
    <tr>
        <td>Average time of the difference loci(ms)</td><td>Average time spent per difference locus (ms)</td>
    </tr>
</table>

Click on the "Download Result" button to download the comparison result CSV file.<br>
Description of the comparison result field:<br>

Average comparison time= Total comparison time/Compare result count<br>
Average time of the difference loci= Average number of difference loci/Total comparison time

## CMD Mode
### Startup Command
<font style="background-color: blue">java -jar fcgs-cmd.jar</font> <font style="background-color: #807f17">microsatellite</font> <font style="background-color: #7e0308">microsatellite-source.csv</font>  <font style="background-color: #7f0f7e">microsatellite-target.csv</font> <font style="background-color: #11807f">0 40</font> 

#### The command format
<font style="background-color: blue">java -jar fcgs-cmd.jar</font> <font style="background-color: #807f17">[Data format]</font> <font style="background-color: #7e0308">[Source fingerprints CSV]</font> </font>  <font style="background-color: #7f0f7e">[Comparison fingerprint CSV]</font> <font style="background-color: #11807f">[Minimum number of difference loci] [Maximum number of difference loci]</font>

The parameter types of the command are described as follows:
<table border="1">
    <tr style="background-color: gray">
        <td width="300">Parameters</td><td>Parameter Description</td>
    </tr>
    <tr>
        <td ><tt style="background-color: #807f17">* Data format</tt></td><td>Molecular marker data type, optional parameter values: Microsatellite, SNP-KASP, SNP-arrays. (Required)</td>
    </tr>
    <tr>
        <td><tt style="background-color: #7e0308">* Source fingerprints CSV</tt></td><td>The path of the comparison fingerprint CSV file. (Required)</td>
    </tr>
    <tr>
        <td><tt style="background-color: #7f0f7e">* Comparison fingerprint CSV</tt></td><td>The path of the comparison fingerprint CSV file. (Required)</td>
    </tr>
    <tr>
        <td><tt style="background-color: #11807f">Minimum number of difference loci</tt></td><td rowspan="2">These two parameters must be both given or not given to filter the alignment results</td>
    </tr>
    <tr>
        <td><tt style="background-color: #11807f">Maximum number of difference loci</tt></td>
    </tr>
    <tr>
        <td colspan="2">Parameters with * are required input parameters</td>
    </tr>
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;After executing the comparison command, the comparison algorithm will save the generated comparison results to the same-level directory in the path of the fcgs-cmd.jar package and store them in CSV data file format.

## Help
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If the user is not clear about the abovementioned documents or requires any technical support, he or she can send an email to jiangbinboy@126.com and contact our technical staff. 
