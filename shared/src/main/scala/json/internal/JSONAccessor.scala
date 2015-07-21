/*
 * Copyright 2015 MediaMath, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package json.internal

import json._

import scala.reflect.{ClassTag, classTag}

/**
 * Created by crgodsey on 7/21/15.
 */
object JSONAccessor {
  def of[T](implicit acc: json.JSONAccessor[T]) = acc

  def create[T: ClassTag, U <: JValue](toJ: T => U,
      fromJ: JValue => T) = new JSONAccessorProducer[T, U] {
    def createJSON(from: T): U = toJ(from)
    def fromJSON(from: JValue): T = fromJ(from)
    def clazz = classTag[T].runtimeClass
    //def fields: IndexedSeq[FieldAccessor[T]] = Nil.toIndexedSeq
  }
}