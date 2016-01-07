<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/xhtml">
    <xsl:output method="xml"/>
    <xsl:template match="Hochschule">
        <html>
            <head>
                <title>Assignment 1</title>
            </head>
            <body>
                <header>
                    <h1>Assignment 1</h1>
                    
                    <ul>
                        <li><a href="#Labore">Labore</a>
                            <ul>
                                <xsl:for-each select="Labor">
                                    <xsl:call-template name="LaborNavEntry" />
                                </xsl:for-each>
                            </ul>
                        </li>
                        <li><a href="#Software">Software</a></li>
                        <li><a href="#Geraete">Andere Geräte</a></li>
                        <li><a href="#Hersteller">Hersteller</a></li>
                        <li><a href="#Statistik">Statistik</a></li>
                    </ul>
                </header>
                <hr />
                
                <h1 id="Labore">Labore</h1>
                <xsl:for-each select="Labor">
                    <div style="margin-left: 50px;">
                    <xsl:call-template name="LaborEntry"/>
                    <hr/>                   
                    </div>
                </xsl:for-each>
                
                <h1 id="Software">Software</h1>
                <table width="100%" border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Beschreibung</th>
                            <th>Besonderheiten</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="//Hochschule/Software">
                            <xsl:call-template name="SoftwareTableEntry"/>
                        </xsl:for-each>
                    </tbody>
                </table>
                <h1 id="Geraete">Sonstige Geräte</h1>
                <table width="100%" border="1">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Beschaffungsdatum</th>
                            <th>Beschreibung</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="//Labor/Scanner | //Labor/Drucker">
                            
                            <xsl:call-template name="OtherDeviceTableEntry"/>
                        </xsl:for-each>
                        
                       
                        
                    </tbody>
                </table>
                <h1 id="Hersteller">Hersteller</h1>
                <table width="25%" border="5">
                    <thead>
                        <tr>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="Labor/Computer/Beschreibung | Labor/Computer/Erweiterung/Beschreibung | Labor/Computer/Software/Beschreibung |Labor/Scanner/Beschreibung | Labor/Drucker/Beschreibung">
                            
                               <!--  <xsl:call-template name="HerstellerEntry"></xsl:call-template>-->
                            <xsl:value-of select="Labor/Computer/Beschreibung"></xsl:value-of>
                                                  
                            
                        </xsl:for-each>
                        
                    </tbody>
                </table>
                <hr/>
                <h1 id="Statistik"></h1>
                <table width="100%" border="1">
                    <thead>
                        <tr>
                            <th>Laboranzahl</th>
                            <th>Computeranzahl</th>
                            <th>Softwareprodukte</th>
                            <th>Durschnittliche Rechneranzahl pro Labor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <div style="margin-left">
                            <xsl:call-template name="LaborCounter"></xsl:call-template>
                            <hr/>                   
                        </div>
                    </tbody>
                </table>
                
                <footer style="background: #ccc;">
                    <p align="center">
                        Michael Guenster, Andre Schriever - 2015
                    </p>
                </footer>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="Hochschule/Labor" name="LaborNavEntry">
        <xsl:variable name="raumnr"><xsl:value-of select="@Raumnummer"/></xsl:variable>
        <li><a href="#{$raumnr}"><xsl:value-of select="@Name"/> - <xsl:value-of select="@Raumnummer"/></a></li>
    </xsl:template>
    <xsl:template match="Hochschule/Labor" name="LaborEntry">
        <xsl:variable name="raumnr"><xsl:value-of select="@Raumnummer"/> </xsl:variable>
        <h2 id="{$raumnr}"><xsl:value-of select="@Name"/> - <xsl:value-of select="@Raumnummer"/></h2>
        <div style="margin-left: 50px;">
        <h3>Computer</h3>
        <table width="100%" border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Betriebssystem</th>
                    <th>Beschaffungsdatum</th>
                    <th>Erweiterungen</th>
                    <th>Installierte Software</th>
                    <th>Beschreibung</th>
                    <th>Besonderheiten</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="Computer">
                    <xsl:sort select="@Name"   /> 
                    <xsl:call-template name="ComputerTableEntry"/>
                </xsl:for-each>
            </tbody>
        </table>
        
        <br/>
        <h3>Software</h3>
        <table width="100%" border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Beschreibung</th>
                    <th>Besonderheiten</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="Computer/Software">
                    <xsl:call-template name="LaborSoftwareTableEntry"/>
                </xsl:for-each>
            </tbody>
        </table>
        
        <br/>
        <h3>Andere Geräte</h3>
        <table width="100%" border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Beschaffungsdatum</th>
                    <th>Beschreibung</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="self::Labor/Scanner | self::Labor/Drucker">
                    
                    <xsl:call-template name="LaborOtherDeviceTableEntry"/>
                </xsl:for-each>
                
<!--                    <xs:call-template name="ComputerTableEntry"/>-->
                
            </tbody>
        </table>
        </div>
    </xsl:template>
    
    <xsl:template  match="Labor/Computer" name="ComputerTableEntry">
        <tr> 
           
        <td><xsl:value-of select="@Name"/></td>
        <td><xsl:value-of select="Betriebssystem"/></td>
        <td><xsl:value-of select="Beschaffungsdatum"/></td>
            <td><xsl:for-each select="Erweiterung">
                <xsl:value-of select="."/>
                <br/>
            </xsl:for-each></td>
        <td><xsl:for-each select="Software">
            <xsl:value-of select="@refID"/>
            <br/>
        </xsl:for-each></td>
        <td><xsl:value-of select="Beschreibung"/></td>
        <td><xsl:value-of select="Besonderheiten"/></td>
          
        </tr>
    </xsl:template>
    
    <xsl:template match="Labor/Software[not(.=preceding::*)]" name="LaborSoftwareTableEntry" >
        
        <xsl:variable name="refID"><xsl:value-of select="@refID"/></xsl:variable>
        <tr>
            <td><xsl:value-of select="@refID"/></td>
            <td><xsl:value-of select="//Hochschule/Software[@ID = $refID]/Name"/></td>
            <td><xsl:value-of select="//Hochschule/Software[@ID = $refID]/Beschreibung"/></td>
            <td><xsl:value-of select="//Hochschule/Software[@ID = $refID]/Besonderheiten"/></td>
        </tr>
    </xsl:template>
    
    <xsl:template match="//Hochschule/Software" name="SoftwareTableEntry" >
        <tr>
            <td><xsl:value-of select="@ID"/></td>
            <td><xsl:value-of select="Name"/></td>
            <td><xsl:value-of select="Beschreibung"/></td>
            <td><xsl:value-of select="Besonderheiten"/></td>
        </tr>
        </xsl:template>
        
    <xsl:template match="//Labor/Scanner | //Labor/Drucker" name="OtherDeviceTableEntry">
        <tr>
            <td><xsl:value-of select="Name"/></td>
            <td><xsl:value-of select="Beschaffungsdatum"/></td>
            <td><xsl:value-of select="Beschreibung"/></td>
        </tr>
    </xsl:template>
    
    <xsl:template  match="//Labor/Scanner | //Labor/Drucker" name="LaborOtherDeviceTableEntry">
        <tr>
            <td><xsl:value-of select="Name"/></td>
            <td><xsl:value-of select="Beschaffungsdatum"/></td>
            <td><xsl:value-of select="Beschreibung"/></td>
        </tr>
    </xsl:template>
    
    <xsl:template match="Labor/Computer/Beschreibung | Labor/Computer/Erweiterung/Beschreibung | Labor/Computer/Software/Beschreibung" name="HerstellerEntry">
        <tr>
            <td><xsl:value-of select="Hersteller"/></td>
        </tr>
    
    </xsl:template>
    
    
        
   
    
    <xsl:template match="Labor/Computer/Erweiterung">
        <p>
            <xsl:value-of select="Beschreibung"/>
            <br/>
            <xsl:value-of select="Besonderheit"/>
            <br/>
            <br/>
        </p>
    </xsl:template>
    
    <xsl:template match="//Hochschule/*" name="LaborCounter">
        <tr>
            <td><xsl:value-of select="count(Labor)"/></td>
            <td><xsl:value-of select="count(./Labor/Computer)"/></td>
            <td><xsl:value-of select="count(Software)"/></td>
            <td><xsl:value-of select="(count(./Labor/Computer) div count(Labor))"/></td>
           
        </tr>
        
    </xsl:template>
    
   
    
</xsl:stylesheet> 