/* SpringProject/src/main/webapp/WEB-INF/js/sakura.js */
// Sakura falling animation
const canvas = document.getElementById('sakuraCanvas');
const ctx = canvas.getContext('2d');
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const numberOfSakura = 100; // 벚꽃 개수
const sakuras = [];

// 벚꽃을 나타내는 클래스
class Sakura {
    constructor() {
        this.x = Math.random() * canvas.width;
        this.y = Math.random() * canvas.height;
        this.size = Math.random() * 8 + 5; // 크기
        this.speed = Math.random() * 0.5 + 0.5; // 속도
        this.wind = Math.random() * 1 - 0.5; // 바람에 의한 좌우 이동
        this.angle = Math.random() * Math.PI * 2; // 회전 각도
    }

    update() {
        // 벚꽃잎의 Y 위치 업데이트
        this.y += this.speed;

        // 벚꽃잎의 X 위치에 바람 효과 추가
        this.x += this.wind;

        // 캔버스를 넘어가면 재설정
        if (this.y > canvas.height) {
            this.y = 0 - this.size;
            this.x = Math.random() * canvas.width;
            this.wind = Math.random() * 1 - 0.5; // 새로운 바람 방향 설정
        }
    }

    draw() {
        ctx.save(); // 현재 상태 저장
        ctx.translate(this.x, this.y); // 위치 이동
        ctx.rotate(this.angle); // 회전 설정

        ctx.beginPath();
        // 벚꽃잎 모양 그리기 (타원형)
        ctx.moveTo(0, 0);
        ctx.bezierCurveTo(-this.size / 2, -this.size * 1.2, -this.size / 2, this.size * 0.8, 0, this.size); // 왼쪽 곡선
        ctx.bezierCurveTo(this.size / 2, this.size * 0.8, this.size / 2, -this.size * 1.2, 0, 0); // 오른쪽 곡선
        ctx.fillStyle = 'rgba(255, 182, 193, 0.7)'; // 연한 분홍색
        ctx.fill();

        ctx.restore(); // 이전 상태 복원
    }
}

// 벚꽃을 생성
function init() {
    for (let i = 0; i < numberOfSakura; i++) {
        sakuras.push(new Sakura());
    }
}

// 캔버스를 업데이트하여 애니메이션을 구현
function animate() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    sakuras.forEach(sakura => {
        sakura.update();
        sakura.draw();
    });
    requestAnimationFrame(animate);
}

init();
animate();

// 창 크기에 따라 캔버스 크기를 동적으로 조정
window.addEventListener('resize', () => {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
});
