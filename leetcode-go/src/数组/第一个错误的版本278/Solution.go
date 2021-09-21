package main

func main() {

}

/**
 * Forward declaration of isBadVersion API.
 * @param   version   your guess about first bad version
 * @return 	 	      true if current version is bad
 *			          false if current version is good
 * func isBadVersion(version int) bool;
 */

func firstBadVersion(n int) int {
	left, right := 0, n
	for left <= right {
		mid := left + (right-left)>>1
		if isBadVersion(mid) {
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	return left
}

func isBadVersion(num int) bool {
	return true
}
