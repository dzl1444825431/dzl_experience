package com.优化;

public class 查找最近的值二分法 {

	public static void main(String[] args) {

		String[] arr = { "A", "B", "C", "D", "U",// 4
				"V",// 5
				"W",// 6
				"W",// 6
				// "X",
				// "Y",
				"Z",// 7
				"Z",// 7

		};
		String a = "X";
		// len(arr, a);
		int len = arr.length;
		int isJump = 0;
		int start = 0;
		int end = arr.length - 1;
		int lastStart = 0;
		int lastEnd = 0;
		int lCount = 0;
		int lastMix = 0;
		int middleValue = 0;
		int middle = 0;
		while (start <= end) {
			lCount++;
			// 中间位置
			middle = (start + end) >> 1; // 相当于(start+end)/2
			// 中值
			middleValue = a.compareTo(arr[middle]);
			if ((lCount != 1) && Math.abs(lastMix) <= Math.abs(middleValue)) {
				break;
			}
			lastMix = middleValue;
			if (middleValue == 0) {
				// 等于中值直接返回
				break;
			} else if (middleValue < 0) {

				end = middle - 1; // 小于中值时在中值前面找

			} else {

				start = middle + 1; // 大于中值在中值后面找

			}
		}
		System.out.println("resp1onse : start = " + start + " end = " + end
				+ " lCount = " + lCount);
//		System.out.println("resp1onse : lastStart = " + lastStart
//				+ " lastEnd = " + lastEnd + " lCount = " + lCount);
		int i = 0;
		if (start != end) {
			i = Math.abs(a.compareTo(arr[start])) > Math.abs(a.compareTo(arr[end])) ? end : start;
			System.out.println("resp1onse : " + i);
		}else {
			i = Math.abs(a.compareTo(arr[start])) > Math.abs(a.compareTo(arr[end - 1])) ? (end - 1) : start;
			System.out.println("resp1onse : " + i);
		}
		System.out.println("resp1onse : " + i + arr[i] + "  middleValue = " + middleValue 
				+ "  lastMix = " + lastMix + "  mid = " + middle);
	}

	static void len(String[] arr, String a) {
		int index = 0;
		int len = arr.length;
		index = len / 2;
		len -= index;

		int lastest = 0;
		int max = 0;
		int mix = 0;
		int lastC = 0;
		while (true) {

			int c = a.compareTo(arr[index]);
			System.out.println("resp1onse : " + a + " vs " + arr[index]
					+ "       index = " + index + "      len = " + len
					+ "     c = " + c);

			if (c == 0) {
				lastest = index;
				break;

			} else if (c > 0) {

				index += (len /= 2);
				max = index;
				if (lastC < 0) {
					break;
				}

			} else {
				index -= (len /= 2);
				mix = index;
				if (lastC > 0) {
					break;
				}
			}
			lastC = c;
			lastest = index;
			if (len == 0) {
				break;
			}

			continue;
		}
		int c1 = a.compareTo(arr[max]);
		int c2 = a.compareTo(arr[mix]);

		int c = Math.abs(c1) < Math.abs(c2) ? max : mix;
		System.out.println("resp1onse : lastest = " + lastest + " " + max + " "
				+ mix + "    c = " + c + " " + arr[c] + c1 + c2);
	}

}
