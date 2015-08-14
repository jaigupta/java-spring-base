package com.djw.web.producer;

import java.lang.annotation.Annotation;

class ProducerNode implements Comparable {
  Class returnClass;
  Annotation annotation;

  public ProducerNode(Class returnClass, Annotation annotation) {
    this.returnClass = returnClass;
    this.annotation = annotation;
  }

  @Override
  public int compareTo(Object target) {
    if (!(target instanceof ProducerNode)) {
      return -1;
    }
    ProducerNode targetProducerNode = (ProducerNode) target;
    // if return classes are not the same, then compare based on the return class name.
    if (!this.returnClass.equals(targetProducerNode.returnClass)) {
      return this.returnClass.getClass().getName()
          .compareTo(targetProducerNode.returnClass.getClass().getName());
    }
    // Now compare based on the annotation hashcodes.
    if (!this.annotation.equals(targetProducerNode.annotation)) {
      return this.annotation.hashCode() - targetProducerNode.annotation.hashCode();
    }
    // finally we can say that the Producer nodes are the same.
    return 0;
  }
}
