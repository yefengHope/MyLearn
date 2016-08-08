<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="roots">
		<xsl:for-each select="djs/dj">
			<fo:root>
				<fo:layout-master-set>
					<fo:simple-page-master master-name="Letter Page"
						page-width="8.500in" page-height="11.000in">
						<fo:region-body region-name="xsl-region-body"
							margin="0.700in" />
						<fo:region-before
							region-name="xsl-region-before" display-align="after"
							extent="0.700in" />
						<fo:region-after region-name="xsl-region-after"
							display-align="before" extent="0.700in" />
						<fo:region-start region-name="xsl-region-start"
							extent="0.700in" />
						<fo:region-end region-name="xsl-region-end"
							extent="0.700in" />
					</fo:simple-page-master>
				</fo:layout-master-set>
				<fo:page-sequence master-reference="Letter Page">
					<fo:static-content flow-name="xsl-region-before"
						font-size="12pt" font-family="Times">
						<fo:block>&#x00A0;</fo:block>
					</fo:static-content>
					<fo:static-content flow-name="xsl-region-after"
						font-size="12pt" font-family="Times">
						<fo:block>&#x00A0;</fo:block>
					</fo:static-content>
					<fo:static-content flow-name="xsl-region-start"
						font-size="12pt" font-family="Times">
						<fo:block>&#x00A0;</fo:block>
					</fo:static-content>
					<fo:static-content flow-name="xsl-region-end"
						font-size="12pt" font-family="Times">
						<fo:block>&#x00A0;</fo:block>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body"
						font-family="Times" font-size="12pt">
						<fo:block font-family="simsun"
							text-align="center" font-weight="bold" font-size="18pt">
							四川省档案管档案记录表
						</fo:block>
						<fo:block font-family="simsun" text-align="left"
							font-weight="normal" font-size="12pt">
							<fo:table border-collapse="collapse"
								width="100%" table-layout="fixed">
								<fo:table-column
									column-width="proportional-column-width(8.57)"
									column-number="1" />
								<fo:table-column
									column-width="proportional-column-width(12.559)"
									column-number="2" />
								<fo:table-column
									column-width="proportional-column-width(14.172)"
									column-number="3" />
								<fo:table-column
									column-width="proportional-column-width(10.08)"
									column-number="4" />
								<fo:table-column
									column-width="proportional-column-width(14.153)"
									column-number="5" />
								<fo:table-column
									column-width="proportional-column-width(14.466)"
									column-number="6" />
								<fo:table-column
									column-width="proportional-column-width(16.466)"
									column-number="7" />
								<fo:table-column
									column-width="proportional-column-width(16.466)"
									column-number="8" />
								<fo:table-column
									column-width="proportional-column-width(12.466)"
									column-number="9" />
								<fo:table-header>
									<fo:table-row>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												序号
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												全宗号
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												全宗名称
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												目号
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												保管期限
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												案卷题名
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												入馆时间
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												存放位置
											</fo:block>
										</fo:table-cell>
										<fo:table-cell
											border="1pt solid black" padding="2pt">
											<fo:block
												text-align="center">
												状态
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-header>
								<fo:table-body>

									<xsl:for-each select="dj-body">
										<fo:table-row>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n1">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n2">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n3">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n4">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n5">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n6">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n7">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n8">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell
												border="1pt solid black" padding="2pt">
												<fo:block>
													<xsl:value-of
														select="dj-n9">
													</xsl:value-of>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</xsl:for-each>
								</fo:table-body>
							</fo:table>
							&#x00A0;
						</fo:block>
						<fo:block font-family="simsun" text-align="left"
							font-weight="normal" font-size="12pt">
							&#x00A0;
						</fo:block>
					</fo:flow>
				</fo:page-sequence>
			</fo:root>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
