package ${packageName};

<#if packageName != mapperScannerPackage>
import ${mapperScannerClass};
</#if>

<#if packageName != crudMapperPackage>
import ${crudMapperClass};
</#if>

<#if packageName != pojoPackage>
import ${pojoClass};
</#if>

@${mapperScannerName}
public interface ${interfaceName} extends CRUDMapper<${pojoName}, ${idName}> {

}
