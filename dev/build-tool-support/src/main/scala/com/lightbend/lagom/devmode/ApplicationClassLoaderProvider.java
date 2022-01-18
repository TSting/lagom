/*
 * Copyright (C) Lightbend Inc. <https://www.lightbend.com>
 */

package com.lightbend.lagom.devmode;

import java.net.URLClassLoader;

public interface ApplicationClassLoaderProvider {
  URLClassLoader get();
}
