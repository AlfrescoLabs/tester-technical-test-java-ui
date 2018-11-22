package org.alfresco.po;

import org.alfresco.common.EnvProperties;
import org.alfresco.utility.web.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Page<T> extends HtmlPage
{
    @Autowired
    protected EnvProperties properties;
}
