package com.example

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

import scala.concurrent.Future


class GroupDBServiceTest extends FunSuite with BeforeAndAfter with MockitoSugar {

  test("Test Groupservice Data") {

    val groupDBService = mock[GroupDBService]

    when(groupDBService.getGroup(1)).thenReturn(Future.successful(Some(Group(1, "1212aa"))))

    val getGroupDetail = groupDBService.getGroup(1)

    assert(getGroupDetail.getClass == Future.successful(Some(Group(1, "1212aa"))))
  }
}
