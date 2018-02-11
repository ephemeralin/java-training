<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <xsl:element name="data">
            <xsl:apply-templates select="data/entries/entry"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="data/entries/entry">
        <xsl:element name="entry-field" >
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>