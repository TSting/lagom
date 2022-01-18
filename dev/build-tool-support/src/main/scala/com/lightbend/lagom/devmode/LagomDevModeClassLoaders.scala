/*
 * Copyright (C) Lightbend Inc. <https://www.lightbend.com>
 */

package com.lightbend.lagom.devmode

import java.io.IOException
import java.lang.reflect.Method
import java.net.URL
import java.net.URLClassLoader
import java.util
import java.util.{Collections, Enumeration, HashSet, Set, Vector}

/**
 * A ClassLoader with a toString() that prints name/urls.
 */
class NamedURLClassLoader(name: String, urls: Array[URL], parent: ClassLoader) extends URLClassLoader(urls, parent) {
  override def toString: String = name + "{" + getURLs.map(_.toString).mkString(", ") + "}"
}

/**
 * A ClassLoader that only uses resources from its parent.
 *
 * The reason we only pull resources from our parent classloader is that the Delegating ClassLoader already uses
 * this classloaders findResources method to locate the resources provided by this ClassLoader, and so our parent
 * will already be returning our resources. Only pulling from the parent ensures we don't duplicate this.
 */
class DelegatedResourcesClassLoader(name: String, urls: Array[URL], parent: ClassLoader)
    extends NamedURLClassLoader(name, urls, parent) {
  require(parent ne null)
  override def getResources(name: String): java.util.Enumeration[java.net.URL] = getParent.getResources(name)
}
