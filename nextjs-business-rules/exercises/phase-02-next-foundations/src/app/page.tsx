const exerciseIds = ["FE-01", "FE-02", "FE-03", "FE-04", "FE-05", "FE-06", "FE-07", "FE-12", "FE-13"];

export default function HomePage() {
  return (
    <>
      <h1>Next.js Business Rules — Phase 02</h1>
      <p>Chọn một competency, hoàn thành TODO trong README rồi tự kiểm tra theo acceptance criteria.</p>
      <ul className="exercise-list">
        {exerciseIds.map((id) => <li key={id}><code>{id}</code></li>)}
      </ul>
      <p><a href="/items">Mở trang luyện API và form Items →</a></p>
    </>
  );
}
