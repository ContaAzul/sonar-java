/*
 * SonarQube Java
 * Copyright (C) 2012-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.syntaxtoken;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.sonar.java.model.JavaTree;
import org.sonar.plugins.java.api.tree.SyntaxToken;
import org.sonar.plugins.java.api.tree.Tree;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LastSyntaxTokenFinder {

  private LastSyntaxTokenFinder() {
  }

  @CheckForNull
  public static SyntaxToken lastSyntaxToken(@Nullable Tree tree) {
    if (tree == null || tree.is(Tree.Kind.INFERED_TYPE)) {
      return null;
    } else if (tree.is(Tree.Kind.TOKEN)) {
      return (SyntaxToken) tree;
    }
    Iterable<Tree> children = ((JavaTree) tree).children();
    Tree last = Iterables.getLast(children, null);
    if (last != null) {
      if (last.is(Tree.Kind.TOKEN)) {
        return (SyntaxToken) last;
      }
      SyntaxToken syntaxToken = lastSyntaxToken(last);
      if (syntaxToken != null) {
        return syntaxToken;
      }
    }
    // we fallback here when the last element is an empty List
    return fallBackWhenEmptyList(children);
  }

  private static SyntaxToken fallBackWhenEmptyList(Iterable<Tree> children) {
    ArrayList<Tree> childrenAsList = Lists.newArrayList(children);
    if (childrenAsList.isEmpty()) {
      return null;
    }
    List<Tree> objects = Lists.reverse(childrenAsList.subList(0, childrenAsList.size() - 1));
    for (Tree next : objects) {
      if (next == null) {
        continue;
      } else if (next.is(Tree.Kind.TOKEN)) {
        return (SyntaxToken) next;
      }
      SyntaxToken syntaxToken = lastSyntaxToken(next);
      if (syntaxToken != null) {
        return syntaxToken;
      }
    }
    return null;
  }
}
