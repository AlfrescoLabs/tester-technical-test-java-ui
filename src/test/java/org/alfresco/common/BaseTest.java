package org.alfresco.common;

import org.alfresco.utility.web.AbstractWebTest;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:technical-test-context.xml")
@Scope(value = "prototype")
public abstract class BaseTest extends AbstractWebTest
{
    @Override
    public String getPageObjectRootPackage()
    {
        return "org/alfresco/po";
    }
}
