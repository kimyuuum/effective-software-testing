package chapter3

class CountClumps {
    // test case 1&2. nums가 빈 배열이거나 null인 경우
    // case 3. nums가 2인데 같은 배열인 경우 or 다른 경우
    // case 4. nums가 긴 배열인 경우..

    fun countClumps(nums: IntArray?): Int {
        if (nums == null || nums.isEmpty() == true) {
            return 0
        }

        var count = 0
        var prev = nums[0]

        var inClump = false

        for (i in 1 until nums.size) {
            if (nums[i] == prev && inClump == false) {
                inClump = true
                count += 1
            }

            if (nums[i] != prev) {
                prev = nums[i]
                inClump = false
            }
        }

        return count
    }
}
