const chatbotToggler = document.querySelector(".chatbot-toggler");
const closeBtn = document.querySelector(".close-btn");
const chatbox = document.querySelector(".chatbox");
const chatInput = document.querySelector(".chat-input textarea");
const sendChatBtn = document.querySelector(".chat-input span");

let userMessage = null; // Variable to store user's message
const inputInitHeight = chatInput.scrollHeight;

// API configuration for Gemini
const API_KEY = "AIzaSyDbP_Km_8FeZ3fl3CAg4z-YcLTvH1zih3k"; // Your API key here
const API_URL = `https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=${API_KEY}`;

// Create chat li elements
const createChatLi = (message, className) => {
    const chatLi = document.createElement("li");
    chatLi.classList.add("chat", `${className}`);
    let chatContent = className === "outgoing" ? `<p></p>` : `<span class="material-symbols-outlined">smart_toy</span><p></p>`;
    chatLi.innerHTML = chatContent;
    chatLi.querySelector("p").textContent = message;
    return chatLi;
};

// cho list sản phẩm vào đây để nó đọc và tư vấn, fix cứng cũng được, hoặc truy vấn database gọi vô đây nó sẽ lưu hoạt hơn
const getProductList = async () => {
    
    return [];
};

const generateResponse = async (chatElement) => {
    const messageElement = chatElement.querySelector("p");

    try {
        const productList = await getProductList();

        const requestBody = {
            contents: [
                {
                    role: "user",
                    parts: [
                        {
                            text: `Hãy nhập vai bạn là CARDLORD SUPPORT nhân viên tư vấn cho của website CARDLORD. CARDLORD là nền tảng học tập trực tuyến
cho phép người dùng tạo và quản lý các bộ flashcard để cải thiện kiến thức. Người dùng có thể lưu trữ bộ flashcard theo
 thẻ phân loại, theo dõi tiến độ học qua các trạng thái (New, Learning, Known, Forgotten) và lưu bộ flashcard vào danh sách yêu thích Các bộ flashcard có thể được chia sẻ công khai hoặc giữ riêng tư.Cùng các tính năng nâng cao cho người dùng VIP.Nhiệm vụ của bạn là tư vấn cho khách hàng trả lời trả lời ngắn gọn,dễ hiểu,trình bày đẹp mắt Khách hàng hỏi ${userMessage}`,
                        },
                    ],
                },
            ],
        };

        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestBody),
        };

        const response = await fetch(API_URL, requestOptions);
        const data = await response.json();

        if (!response.ok) throw new Error(data.error.message);

        if (data.candidates && data.candidates.length > 0) {
            messageElement.innerHTML = data.candidates[0].content.parts[0].text.replace(/\*\*(.*?)\*\*/g, "$1");
        } else {
            messageElement.textContent = "Không có phản hồi hợp lệ.";
        }
    } catch (error) {
        messageElement.classList.add("error");
        messageElement.textContent = error.message;
    }

    chatbox.scrollTo(0, chatbox.scrollHeight);  // Ensure chat scrolls to the latest message
};

const handleChat = () => {
    userMessage = chatInput.value.trim();
    if (!userMessage) return;

    chatInput.value = "";
    chatInput.style.height = `${inputInitHeight}px`;

    chatbox.appendChild(createChatLi(userMessage, "outgoing"));
    chatbox.scrollTo(0, chatbox.scrollHeight);

    setTimeout(() => {
        const incomingChatLi = createChatLi("Thinking...", "incoming");
        chatbox.appendChild(incomingChatLi);
        chatbox.scrollTo(0, chatbox.scrollHeight);
        generateResponse(incomingChatLi);
    }, 600);
};

chatInput.addEventListener("input", () => {
    chatInput.style.height = `${inputInitHeight}px`;
    chatInput.style.height = `${chatInput.scrollHeight}px`;
});

chatInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
        e.preventDefault();
        handleChat();
    }
});

sendChatBtn.addEventListener("click", handleChat);
closeBtn.addEventListener("click", () => document.body.classList.remove("show-chatbot"));
chatbotToggler.addEventListener("click", () => document.body.classList.toggle("show-chatbot"));
