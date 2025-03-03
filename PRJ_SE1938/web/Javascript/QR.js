let courses = [
    {
      courseID: "VIP001",
      courseAvatar: "ImageDemo/anh-dai-dien-90-780x450.jpg",
      courseName: "Nâng VIP II ưu đãi 1 tháng",
      coursePrice: 20000,
    },
    {
      courseID: "VIP002",
      courseAvatar: "ImageDemo/anh-mau-xanh-duong.jpg",
      courseName: "Nâng VIP II ưu đãi 3 tháng",
      coursePrice: 50000,
    },
    {
      courseID: "VIP003",
      courseAvatar: "ImageDemo/hinh-nen-anime-16.jpg",
      courseName: "Nâng VIP III ưu đãi 12 tháng",
      coursePrice: 100000,
    }
  ];
  
  let MY_BANK = {
    BANK_ID: "MB",
    ACCOUNT_NO: "0965122004"
  };
  
  document.addEventListener("DOMContentLoaded", () => {
    const courseInner = document.querySelector(".courses_inner");
    let coursesRenderUI = "";
    courses.forEach((item, index) => {
      coursesRenderUI += `
        <div class="course_item">
          <img src="${item.courseAvatar}">
          <p>${item.courseName}</p>
          <p>${item.coursePrice}</p>
          <a class="course_item_btn">Mua</a>
        </div>
      `;
    });
    courseInner.innerHTML = coursesRenderUI;

  const btns = document.querySelectorAll(".course_item_btn");
  const paid_content = document.getElementById("paid_content");
  const paid_price = document.getElementById("paid_price");
  const course_qr_img = document.querySelector(".course_qr_img");

  const courseQr = document.getElementById("course_qr");
  const toastOverlay = document.getElementById("toast_overlay");
  const closeToast = document.getElementById("close_toast");
  const countdownTimer = document.getElementById("countdown_timer");


  let checkInterval = null; 
  let countdownInterval = null; 
  


  function showToast() {
    courseQr.classList.add("show");
    toastOverlay.classList.add("show");
  }

  // Ẩn form
  function hideToast() {
    courseQr.classList.remove("show");
    toastOverlay.classList.remove("show");
    clearInterval(checkInterval);
    clearInterval(countdownInterval);
  }




  function startCountdown(duration) {
    let timeLeft = duration; // Tính bằng giây
    countdownInterval = setInterval(() => {
      const minutes = Math.floor(timeLeft / 60);
      const seconds = timeLeft % 60;
      countdownTimer.textContent = `${minutes}:${seconds < 10 ? "0" : ""}${seconds}`;
      if (timeLeft <= 0) {
        clearInterval(countdownInterval);
        alert("Hết thời gian!");
      }
      timeLeft--;
    }, 1000);
  }

  closeToast.addEventListener("click", hideToast);

  // Bắt sự kiện click nút Mua
  document.querySelectorAll(".course_item_btn").forEach((btn, index) => {
    btn.addEventListener("click", () => {
      showToast(); // Hiển thị form
      startCountdown(300); // Đếm ngược 5 phút
    });
  });

  // // Bắt sự kiện click overlay để đóng form
  // toastOverlay.addEventListener("click", hideToast);

const API_KEY = "AK_CS.28bdca90d00711efa35d3bccdd557a34.lwqKmceInovIbLqZ9NccS5N3jnYTCLhhyr87ZYHlwTxvMzvzmN2VLseFplAdMKl8QYHbAzGg";
const API_GET_PAID = "https://oauth.casso.vn/v2/transactions";




// async function checkPaid(paidPrice, paidContent) {
//   try {
//     const response = await fetch(API_GET_PAID, {
//       headers: {
//         Authorization: `apikey ${API_KEY}`,
//         "Content-Type": "application/json",
//       },
//     });

//     if (!response.ok) {
//       throw new Error(`HTTP error! status: ${response.status}`);
//     }

//     const result = await response.json();

//     // Log dữ liệu API để kiểm tra
//     console.log("API response:", result);

//     // Lấy danh sách giao dịch từ `data.records`
//     const transactions = result.data.records;
//     if (!transactions || !Array.isArray(transactions)) {
//       throw new Error("Transactions data is not an array.");
//     }

//     // Log chi tiết các giao dịch
//     console.log("Transactions:", transactions);

//     // Tìm giao dịch khớp với điều kiện
//     const transaction = transactions.find((item) => {
//       const isDescriptionMatch = item.description.includes(paidContent);
//       const isAmountMatch = item.amount >= paidPrice;

//       // Log kiểm tra từng điều kiện
//       console.log(`Checking transaction: ${item.description}`);
//       console.log(`Description match: ${isDescriptionMatch}`);
//       console.log(`Amount match (${item.amount} >= ${paidPrice}): ${isAmountMatch}`);

//       return isDescriptionMatch && isAmountMatch;
//     });

//     if (transaction) {
//       hideToast();
//       clearInterval(checkInterval); // Dừng kiểm tra

//       showSuccessToast("Thanh toán thành công");
//     } else {
//       console.log("Chưa có giao dịch phù hợp.");
//     }
//   } catch (error) {
//     console.error("Error fetching transactions:", error);
//   }
// }





btns.forEach((item, index) => {
  item.addEventListener("click", () => {
    const paidContent = `${courses[index].courseID}userID2k3`;
    const paidPrice = courses[index].coursePrice;

    let QR = `https://img.vietqr.io/image/${MY_BANK.BANK_ID}-${MY_BANK.ACCOUNT_NO}-qr_only.png?amount=${paidPrice}&addInfo=${paidContent}`;
    course_qr_img.src = QR;
    paid_content.value = paidContent;
    paid_price.value = paidPrice;

    showToast();

    setTimeout(() => {
      checkInterval = setInterval(() => {
        checkPaid(paidPrice, paidContent);
      }, 1000);
    }, 10000);
  });
});

window.copyToClipboard = (id) => {
  const input = document.getElementById(id);
  input.select();
  input.setSelectionRange(0, 99999); // Dành cho di động
  navigator.clipboard.writeText(input.value);
  alert("Copied: " + input.value);
};



// Hàm hiển thị toast
function showSuccessToast(message = "Thanh toán thành công!") {
  const toast = document.getElementById("success_toast");

  // Cập nhật nội dung thông báo
  toast.querySelector(".toast_message p").textContent = message;

  // Hiển thị toast với hiệu ứng
  toast.classList.add("show");

  // Tự động ẩn sau 3 giây
  setTimeout(() => {
    hideSuccessToast();
  }, 10000);
}

// Hàm ẩn toast
function hideSuccessToast() {
  const toast = document.getElementById("success_toast");
  toast.classList.remove("show");
}




})
