import { ActivityTable } from "@/components/dashboard/activity-table";
import { InsertActivityForm } from "@/components/dashboard/insert-activity-form";
import { ActivityBalance } from "@/components/dashboard/activity-balance";

export default function Dashboard(){
    return (
        <>
            <InsertActivityForm />
            <ActivityTable />
            <ActivityBalance />
        </>
    );
}