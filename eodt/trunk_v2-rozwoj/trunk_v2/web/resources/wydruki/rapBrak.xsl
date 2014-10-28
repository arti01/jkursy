<?xml version='1.0' encoding='UTF-8' ?> 
<xsl:stylesheet version="1.1"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:barcode="org.krysalis.barcode4j.xalan.BarcodeExt" xmlns:common="http://exslt.org/common"
                xmlns:xalan="http://xml.apache.org" exclude-result-prefixes="barcode common xalan" xmlns:rx="http://www.renderx.com/XSL/Extensions">
    <xsl:template match="DcDokPocztaList">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-landscape"  margin-left="0.5cm" margin-right="0.5cm" page-height="21cm" page-width="29.7cm">
                    <fo:region-body margin-top="0.5cm" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4-landscape">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Arial" font-size="11pt" font-weight="normal">
                        <fo:table>
                            <fo:table-column column-number="1" column-width="1cm" />
                            <fo:table-column column-number="2" column-width="5cm" />
                            <fo:table-header>

                                <fo:table-row>
                                    <fo:table-cell text-align="center" margin-left="0.2cm" border-left="solid">
                                        <fo:block>lp</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" margin-left="0.2cm" border-left="solid">
                                        <fo:block>nazwa</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-header>                           
                            
                            <fo:table-body>
                                <xsl:for-each select="./dokumentList/dcDokPoczta">
                                    <fo:table-row border="solid 0.1mm black">
                                        <fo:table-cell text-align="right" border="solid 0.3mm black" margin="0.2cm">
                                            <fo:block>
                                                <xsl:value-of select="id" />.
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="left" border="solid 0.3mm black" margin="0.2cm">
                                            <fo:block font-size="8pt">
                                                <xsl:value-of select="nazwa" />
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
 
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>