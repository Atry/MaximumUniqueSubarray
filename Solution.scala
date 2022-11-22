object Solution {
    def main(args: Array[String]): Unit = {
        maximumUniqueSubarray((1 to 10000).toArray)
    }

    def maximumUniqueSubarray(nums: Array[Int]): Int = {
        val (trailingStart, sum, reverseMap, bestResult) =
            nums.indices.foldLeft((0, 0, collection.mutable.HashMap.empty[Int, Int], 0)) {
                case ((trailingStart, sum, reverseMap, bestResult), index) =>
                    val char = nums(index)
                    reverseMap.get(char) match {
                        case None =>
                            (trailingStart, sum + char, reverseMap += char -> index, bestResult)
                        case Some(previousIndex) =>
                            val newStart = previousIndex + 1
                            val dropped = (nums: IndexedSeq[Int]).view(trailingStart, newStart)
                            locally {
                                (
                                    previousIndex + 1,
                                    sum - dropped.sum + char,
                                    (reverseMap --= dropped += char -> index),
                                    math.max(
                                        sum,
                                        bestResult
                                    )
                                )
                            }
                    }
            }
        math.max(sum, bestResult)
    }
}